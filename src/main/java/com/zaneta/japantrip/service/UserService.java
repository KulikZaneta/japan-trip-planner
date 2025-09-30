package com.zaneta.japantrip.service;

import com.zaneta.japantrip.model.User;
import com.zaneta.japantrip.model.dto.user.UserPatchRequest;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User getUserById(UUID userId);

    List<User> getUsers();

    User createUser(User user);

    User updateUser(User user);

    User patchUser(UUID userId, UserPatchRequest request);

    void deleteUserById(UUID userId);

}

