package com.cesarlead.users.service;

import com.cesarlead.users.model.dtos.UserRequestDto;
import com.cesarlead.users.model.dtos.UserResponseDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserEntityService {

    List<UserResponseDto> getAllUsers();

    Optional<UserResponseDto> findById(UUID id);

    Optional<UserResponseDto> getUserByUsername(String username);

    Optional<UserResponseDto> getUserByEmail(String email);

    UserResponseDto createUser(UserRequestDto user);

    UserResponseDto updateUser(UUID id, UserResponseDto user);

    void deleteUser(UUID id);
}
