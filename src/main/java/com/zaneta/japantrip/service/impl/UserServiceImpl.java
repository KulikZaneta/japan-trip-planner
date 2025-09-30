package com.zaneta.japantrip.service;

import com.zaneta.japantrip.exception.InvalidFieldException;
import com.zaneta.japantrip.mapper.user.UserPatchRequestMapper;
import com.zaneta.japantrip.model.Role;
import com.zaneta.japantrip.model.User;
import com.zaneta.japantrip.model.dto.user.UserPatchRequest;
import com.zaneta.japantrip.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserPatchRequestMapper userPatchRequestMapper;

    public UserServiceImpl(UserRepository userRepository, UserPatchRequestMapper userPatchRequestMapper) {
        this.userRepository = userRepository;
        this.userPatchRequestMapper = userPatchRequestMapper;
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
    }

    @Override
    public List<User> getUsersById() {
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

    @Transactional
    @Override
    public User patchUser(UUID id, UserPatchRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id :" + id + " not found"));

        Map<String, Object> fields = new HashMap<>();
        fields.put("firstName", request.getFirstName());
        fields.put("lastName", request.getLastName());
        fields.put("nickname", request.getNickname());
        fields.put("email", request.getEmail());
        fields.put("password", request.getPassword());

        fields.forEach((field, value) -> {
            if (value instanceof String str && str.isBlank()) {
                    throw new InvalidFieldException(field + " cannot be null");
            }
        });

        userPatchRequestMapper.mapToUser(request, user);

        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }


}

