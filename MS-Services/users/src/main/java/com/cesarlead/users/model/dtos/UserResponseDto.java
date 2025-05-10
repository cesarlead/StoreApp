package com.cesarlead.users.model.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder
public record UserResponseDto(

        UUID id,

        @NotBlank(message = "Invalid username")
        String username,

        @NotBlank(message = "Invalid email")
        @Email(message = "Invalid email")
        String email,

        @NotBlank(message = "Invalid status")
        String status,

        Date createdAt
) {
}
