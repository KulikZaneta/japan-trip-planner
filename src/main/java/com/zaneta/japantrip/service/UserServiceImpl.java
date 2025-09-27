package com.zaneta.japantrip.service;

import com.zaneta.japantrip.model.Role;
import com.zaneta.japantrip.model.User;
import com.zaneta.japantrip.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User createUser(User user) {
        user.setRole(Role.ROLE_USER);

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        userRepository.findById(user.getUserId()).orElseThrow(() -> new EntityNotFoundException("User with id :" + user.getUserId() + " not found"));
        if (user.getRole() == null) {
            user.setRole(Role.ROLE_USER);
        }
        return userRepository.save(user);
    }

    @Override
    public User patchUser(User user) {
        userRepository.findById(user.getUserId()).orElseThrow(() -> new EntityNotFoundException("User with id :" + user.getUserId() + " not found"));
        if (user.getRole() == null) {
            user.setRole(Role.ROLE_USER);
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }


}

