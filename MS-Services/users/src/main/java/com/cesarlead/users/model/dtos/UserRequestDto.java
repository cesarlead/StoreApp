package com.cesarlead.users.model.dtos;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record UserRequestDto(

    @NotBlank(message = "Invalid username")
    @Size(min = 3, max = 20)
    String username,

    @NotBlank(message = "Invalid email")
    @Email(message = "Invalid email")
    String email,

    @NotBlank(message = "Invalid password")
    @Size(min = 8, max = 20)
    String passwordHash,

    @NotBlank(message = "Invalid full name")
    @Size(min = 3, max = 50)
    String fullName,

    @NotBlank(message = "Invalid address")
    String address
) {}
