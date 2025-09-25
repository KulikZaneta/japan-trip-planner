package com.zaneta.japantrip;

import com.zaneta.japantrip.mapper.UserResponseMapper;
import com.zaneta.japantrip.model.User;
import com.zaneta.japantrip.model.dto.user.UserResponse;
import com.zaneta.japantrip.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/japan")
public class UserController {

    private final UserResponseMapper userResponseMapper;

    private final UserService userService;

    public UserController(UserResponseMapper userResponseMapper, UserService userService) {
        this.userResponseMapper = userResponseMapper;
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(userResponseMapper.mapToUserResponse(user));
    }

}

