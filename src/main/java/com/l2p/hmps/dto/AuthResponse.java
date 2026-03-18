package com.l2p.hmps.dto;

import com.l2p.hmps.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
	
    private String accessToken;
    private String refreshToken;
    private Role role;
    private String email;
    
}
