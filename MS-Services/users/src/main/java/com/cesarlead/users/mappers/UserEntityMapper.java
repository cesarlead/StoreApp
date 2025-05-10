package com.cesarlead.users.mappers;

import com.cesarlead.users.model.dtos.UserRequestDto;
import com.cesarlead.users.model.dtos.UserResponseDto;
import com.cesarlead.users.model.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserEntityMapper {

    public UserResponseDto fromEntity(UserEntity user) {
        if(user != null) {
            return UserResponseDto.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .status(user.getStatus())
                    .createdAt(user.getCreatedAt())
                    .build();
        }
        throw new IllegalArgumentException("Entity inválida");
    }

    public UserEntity toEntity(UserRequestDto dto) {
        if (dto != null) {
            return UserEntity.builder()
                    .username(dto.username())
                    .email(dto.email())
                    .passwordHash(dto.passwordHash())
                    .fullName(dto.fullName())
                    .address(dto.address())
                    .status("active")
                    .createdAt(new Date())
                    .build();
        }
        throw new IllegalArgumentException("DTO inválido");
    }
}
