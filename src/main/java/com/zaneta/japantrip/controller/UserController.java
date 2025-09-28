package com.zaneta.japantrip.controller;

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
@RequestMapping("/users")
public class UserController {

    private final UserResponseMapper userResponseMapper;

    private final UserRegistrationMapper userRegistrationMapper;

    private final UserUpdateRequestMapper userUpdateRequestMapper;

    private final UserService userService;

    public UserController(UserResponseMapper userResponseMapper, UserRegistrationMapper userRegistrationMapper, UserUpdateRequestMapper userUpdateRequestMapper, UserService userService) {
        this.userResponseMapper = userResponseMapper;
        this.userRegistrationMapper = userRegistrationMapper;
        this.userUpdateRequestMapper = userUpdateRequestMapper;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(userResponseMapper.mapToUserResponse(user));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRegistrationRequest request) {
        User user = userRegistrationMapper.mapToUser(request);

        User savedUser = userService.createUser(user);

        UserResponse response = userResponseMapper.mapToUserResponse(savedUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody @Valid UserUpdateRequest request) {
        User user = userUpdateRequestMapper.mapToUser(request);
        user.setUserId(id);

        User updatedUser = userService.updateUser(user);

        return ResponseEntity.ok(userResponseMapper.mapToUserResponse(updatedUser));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody @Valid UserPatchRequest request) {

        User patchUser = userService.patchUser(id, request);

        UserResponse response = userResponseMapper.mapToUserResponse(patchUser);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

