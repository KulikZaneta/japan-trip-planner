package com.zaneta.japantrip.controller;

import com.zaneta.japantrip.mapper.user.UserRegistrationMapper;
import com.zaneta.japantrip.mapper.user.UserResponseMapper;
import com.zaneta.japantrip.mapper.user.UserUpdateRequestMapper;
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

import java.util.List;
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
        UserResponse userResponse = userResponseMapper.toUserResponse(user);

        return ResponseEntity.ok(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser() {
        List<User> users = userService.getUsers();
        List<UserResponse> userResponses = userResponseMapper.toUserResponseList(users);

        return ResponseEntity.ok(userResponses);
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRegistrationRequest request) {
        User user = userRegistrationMapper.toUser(request);
        User savedUser = userService.createUser(user);
        UserResponse userResponse = userResponseMapper.toUserResponse(savedUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody @Valid UserUpdateRequest request) {
        User user = userUpdateRequestMapper.toUser(request);
        user.setUserId(id);

        User updatedUser = userService.updateUser(user);
        UserResponse userResponse = userResponseMapper.toUserResponse(updatedUser);

        return ResponseEntity.ok(userResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID id, @RequestBody @Valid UserPatchRequest request) {
        User patchUser = userService.patchUser(id, request);
        UserResponse response = userResponseMapper.toUserResponse(patchUser);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        userService.deleteUserById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

