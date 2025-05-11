package com.cesarlead.users.controller;

import com.cesarlead.users.model.dtos.UserRequestDto;
import com.cesarlead.users.model.dtos.UserResponseDto;
import com.cesarlead.users.model.exception.NotFoundException;
import com.cesarlead.users.service.UserEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@Validated
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Users", description = "Users API")
public class UserEntityController {

    private static final String MESSAGE = "Requesting user";
    private final UserEntityService service;

    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        log.info("Requesting all users");
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Get user by username")
    public ResponseEntity<UserResponseDto> getUserByUsername(@PathVariable String username) {
        log.info(MESSAGE + " {}", username);
        return ResponseEntity.ok(service.getUserByUsername(username)
                .orElseThrow(() -> new NotFoundException("User", username)));
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get user by email")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable String email) {
        log.info(MESSAGE + " {}", email);
        return ResponseEntity.ok(service.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User", email)));
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get user by id")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id) {
        log.info(MESSAGE + " {}", id);
        return ResponseEntity.ok(service.findById(id)
                .orElseThrow(() -> new NotFoundException("User", id)));
    }

    @PostMapping
    @Operation(summary = "Create user")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto user) {
        log.info("Creating user {}", user.email());
        return ResponseEntity.created(URI.create("/" + service.createUser(user).username())).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user")
    public ResponseEntity<UserResponseDto> updateUser(@Valid @PathVariable UUID id, @Valid @RequestBody UserResponseDto user) {
        log.info("Updating user {}", id);
        return ResponseEntity.ok(service.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        log.info("Deleting user {}", id);
        service.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
