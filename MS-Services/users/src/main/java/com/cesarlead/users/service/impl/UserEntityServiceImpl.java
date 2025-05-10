package com.cesarlead.users.service.impl;

import com.cesarlead.users.mappers.UserEntityMapper;
import com.cesarlead.users.model.dtos.UserRequestDto;
import com.cesarlead.users.model.dtos.UserResponseDto;
import com.cesarlead.users.model.exception.NotFoundException;
import com.cesarlead.users.repository.UserEntityRepository;
import com.cesarlead.users.service.UserEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserEntityServiceImpl implements UserEntityService {

    private final UserEntityRepository repository;
    private final UserEntityMapper mapper;

    @Override
    public List<UserResponseDto> getAllUsers() {
        log.info("Processing getAllUsers");
        return repository.findAll().stream().map(mapper::fromEntity).toList();
    }
    @Override
    public Optional<UserResponseDto> findById(UUID id) {
        log.info("Processing getUserById {}", id);
        return repository.findById(id).map(mapper::fromEntity);
    }

    @Override
    public Optional<UserResponseDto> getUserByUsername(String username) {
        log.info("Processing getUserByUsername {}", username);
        return repository.findByUsername(username).map(mapper::fromEntity);
    }

    @Override
    public Optional<UserResponseDto> getUserByEmail(String email) {
        log.info("Processing getUserByEmail {}", email);
        return  repository.findByEmail(email).map(mapper::fromEntity);
    }

    @Override
    public UserResponseDto createUser(UserRequestDto user) {
        log.info("Processing createUser {}", user);
        var entity = repository.findByEmail(user.email());
        if(entity.isEmpty()) {
            return mapper.fromEntity(repository.save(mapper.toEntity(user)));
        }
        throw new IllegalArgumentException("User already exists");
    }

    @Override
    public UserResponseDto updateUser(UUID id, UserResponseDto user) {
        log.info("Processing updateUser {} {}", id, user);
        var entity = repository.findById(id);
        if(entity.isPresent()) {
            var userEntity = entity.get();
            userEntity.setUsername(user.username());
            userEntity.setEmail(user.email());
            userEntity.setStatus(user.status());
            return mapper.fromEntity(repository.save(userEntity));
        }
        throw new NotFoundException("User", id);
    }

    @Override
    public void deleteUser(UUID id) {
        log.info("Processing deleteUser {}", id);
        var entity = repository.findById(id);
        if(entity.isPresent()) {
            repository.delete(entity.get());
            return;
        }
        throw new NotFoundException("User", id);
    }
}
