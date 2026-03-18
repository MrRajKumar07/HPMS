package com.l2p.hmps.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.l2p.hmps.dto.AuthResponse;
import com.l2p.hmps.dto.RegisterRequest;
import com.l2p.hmps.dto.UserResponse;
import com.l2p.hmps.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * TO ENTITY: Converts Registration DTO to User Entity.
     * We ignore password here because it MUST be BCrypt encoded manually 
     * in the Service layer before saving.
     */
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    User toEntity(RegisterRequest request);

    /**
     * TO DTO: Converts User Entity to AuthResponse.
     * Tokens are ignored because they are generated dynamically by JwtUtils.
     */
    @Mapping(target = "accessToken", ignore = true)
    @Mapping(target = "refreshToken", ignore = true)
    AuthResponse toAuthResponse(User user);

    /**
     * TO DTO: Converts User Entity to a safe profile response.
     */
    UserResponse toUserResponse(User user);
}