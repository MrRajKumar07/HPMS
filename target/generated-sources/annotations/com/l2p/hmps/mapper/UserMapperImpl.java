package com.l2p.hmps.mapper;

import com.l2p.hmps.dto.AuthResponse;
import com.l2p.hmps.dto.RegisterRequest;
import com.l2p.hmps.dto.UserResponse;
import com.l2p.hmps.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-21T03:03:51+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(RegisterRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( request.getEmail() );
        user.role( request.getRole() );

        return user.build();
    }

    @Override
    public AuthResponse toAuthResponse(User user) {
        if ( user == null ) {
            return null;
        }

        AuthResponse.AuthResponseBuilder authResponse = AuthResponse.builder();

        authResponse.role( user.getRole() );
        authResponse.email( user.getEmail() );

        return authResponse.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.email( user.getEmail() );
        userResponse.role( user.getRole() );

        return userResponse.build();
    }
}
