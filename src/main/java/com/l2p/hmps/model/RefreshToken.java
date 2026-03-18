package com.l2p.hmps.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "refresh_tokens")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id; // 

    @Column(unique = true, nullable = false)
    private String token; // Unique UUID string 

    @Column(name = "user_id", nullable = false)
    private UUID userId; // FK -> users.id 

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt; // Set to now() + 7 days in service 

    @Builder.Default
    private boolean used = false; // Invalidated after first use 
}