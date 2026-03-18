package com.l2p.hmps.service;

import com.l2p.hmps.dto.AuthResponse;
import com.l2p.hmps.dto.ChangePasswordRequest;
import com.l2p.hmps.dto.LoginRequest;
import com.l2p.hmps.dto.RegisterRequest;
import com.l2p.hmps.dto.RefreshTokenRequest;
import java.util.UUID;

public interface AuthService {
    // Phase 1 Core Auth
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    AuthResponse refreshToken(RefreshTokenRequest request);
    void logout(UUID userId);
    
    // User Management & Security
    void changePassword(UUID userId, ChangePasswordRequest request);
}