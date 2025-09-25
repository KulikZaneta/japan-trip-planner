package com.zaneta.japantrip.service;

import com.zaneta.japantrip.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    User getUserById(UUID id);

    List<User> getUsers();

}

