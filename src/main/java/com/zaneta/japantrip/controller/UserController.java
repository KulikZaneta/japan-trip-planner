package com.zaneta.japantrip.controller;

import com.zaneta.japantrip.mapper.UserPatchRequestMapper;
import com.zaneta.japantrip.mapper.UserRegistrationMapper;
import com.zaneta.japantrip.mapper.UserResponseMapper;
import com.zaneta.japantrip.mapper.UserUpdateRequestMapper;
import com.zaneta.japantrip.model.User;
import com.zaneta.japantrip.model.dto.user.UserPatchRequest;
import com.zaneta.japantrip.model.dto.user.UserRegistrationRequest;
import com.zaneta.japantrip.model.dto.user.UserResponse;
import com.zaneta.japantrip.model.dto.user.UserUpdateRequest;
import com.zaneta.japantrip.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/japan")
public class UserController {

    private final UserResponseMapper userResponseMapper;

    private final UserRegistrationMapper userRegistrationMapper;

    private final UserUpdateRequestMapper userUpdateRequestMapper;

    private final UserPatchRequestMapper userPatchRequestMapper;

    private final UserService userService;

    public UserController(UserResponseMapper userResponseMapper, UserRegistrationMapper userRegistrationMapper, UserUpdateRequestMapper userUpdateRequestMapper, UserPatchRequestMapper userPatchRequestMapper, UserService userService) {
        this.userResponseMapper = userResponseMapper;
        this.userRegistrationMapper = userRegistrationMapper;
        this.userUpdateRequestMapper = userUpdateRequestMapper;
        this.userPatchRequestMapper = userPatchRequestMapper;
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(userResponseMapper.mapToUserResponse(user));
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRegistrationRequest request) {
        User user = userRegistrationMapper.mapToUser(request);

        User savedUser = userService.createUser(user);

        UserResponse response = userResponseMapper.mapToUserResponse(savedUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody @Valid UserUpdateRequest request) {
        User user = userUpdateRequestMapper.mapToUser(request);
        user.setUserId(id);

        User updatedUser = userService.updateUser(user);

        return ResponseEntity.ok(userResponseMapper.mapToUserResponse(updatedUser));
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody @Valid UserPatchRequest request) {
        User user = userPatchRequestMapper.mapToUser(request);
        user.setUserId(id);

        User patchUser = userService.patchUser(user);

        return ResponseEntity.ok(userResponseMapper.mapToUserResponse(patchUser));
    }

}

