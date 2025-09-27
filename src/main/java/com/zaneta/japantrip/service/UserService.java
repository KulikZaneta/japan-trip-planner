package com.zaneta.japantrip.service;

import com.zaneta.japantrip.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User getUserById(UUID id);

    List<User> getUsers();

    User createUser(User user);

    User updateUser(User user);

    User patchUser(User user);

    void deleteUserById(UUID id);

}

