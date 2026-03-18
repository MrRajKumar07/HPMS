package com.l2p.hmps.service;

import com.l2p.hmps.config.JwtUtils;
import com.l2p.hmps.dto.AuthResponse;
import com.l2p.hmps.dto.ChangePasswordRequest;
import com.l2p.hmps.dto.LoginRequest;
import com.l2p.hmps.dto.RefreshTokenRequest;
import com.l2p.hmps.dto.RegisterRequest;
import com.l2p.hmps.exception.AuthException;
import com.l2p.hmps.mapper.UserMapper;
import com.l2p.hmps.model.RefreshToken;
import com.l2p.hmps.model.User;
import com.l2p.hmps.repository.RefreshTokenRepository;
import com.l2p.hmps.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        // 1. Check for duplicate email 
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AuthException("Email already registered", HttpStatus.CONFLICT, "AUTH_409");
        }

        // 2. Validate Password Match (Requested Extra Field)
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new AuthException("Passwords do not match", HttpStatus.BAD_REQUEST, "AUTH_400");
        }

        // 3. Map DTO to Entity and Encode Password 
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);

        return generateTokensAndResponse(user);
    }

    @Override
    @Transactional
    public AuthResponse login(LoginRequest request) {
        // 1. Authenticate using Spring Security 
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // 2. Retrieve User
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new AuthException("User not found", HttpStatus.UNAUTHORIZED, "AUTH_401"));

        return generateTokensAndResponse(user);
    }

    @Override
    @Transactional
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        // 1. Find and validate the Refresh Token 
        RefreshToken token = refreshTokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new AuthException("Invalid refresh token", HttpStatus.UNAUTHORIZED, "AUTH_401"));

        if (token.isUsed() || token.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new AuthException("Refresh token expired or used", HttpStatus.UNAUTHORIZED, "AUTH_401");
        }

        // 2. Mark token as used (Single-use policy) [cite: 30, 36]
        token.setUsed(true);
        refreshTokenRepository.save(token);

        // 3. Issue new Access Token
        User user = userRepository.findById(token.getUserId())
                .orElseThrow(() -> new AuthException("User not found", HttpStatus.UNAUTHORIZED, "AUTH_401"));

        return generateTokensAndResponse(user);
    }

    @Override
    @Transactional
    public void logout(UUID userId) {
        // Remove all active refresh tokens for the user 
        refreshTokenRepository.deleteByUserId(userId);
    }

    @Override
    @Transactional
    public void changePassword(UUID userId, ChangePasswordRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AuthException("User not found", HttpStatus.NOT_FOUND, "AUTH_404"));

        // 1. Verify current password 
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new AuthException("Invalid current password", HttpStatus.BAD_REQUEST, "AUTH_400");
        }

        // 2. Verify new password confirmation
        if (!request.getNewPassword().equals(request.getConfirmNewPassword())) {
            throw new AuthException("New passwords do not match", HttpStatus.BAD_REQUEST, "AUTH_400");
        }

        // 3. Update and Save 
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }

    // Helper method to consolidate response generation
    private AuthResponse generateTokensAndResponse(User user) {
        String accessToken = jwtUtils.generateAccessToken(user); // 15 min 
        String refreshTokenStr = UUID.randomUUID().toString();

        RefreshToken refreshToken = RefreshToken.builder()
                .token(refreshTokenStr)
                .userId(user.getId())
                .expiresAt(LocalDateTime.now().plusDays(7)) // 7 days 
                .build();
        refreshTokenRepository.save(refreshToken);

        AuthResponse response = userMapper.toAuthResponse(user);
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshTokenStr);
        return response;
    }
}