package com.l2p.hmps.repository;

import com.l2p.hmps.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    
    // Used to validate the token during the /refresh flow [cite: 34, 36]
    Optional<RefreshToken> findByToken(String token);
    
    // Used during logout to invalidate all active sessions for a user [cite: 34, 36]
    @Modifying
    @Transactional
    void deleteByUser_Id(UUID userId);
    
    // Called by a scheduled job to purge old tokens from the DB [cite: 34, 238]
    @Modifying
    @Transactional
    void deleteByExpiresAtBefore(LocalDateTime now);
}