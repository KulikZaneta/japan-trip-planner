package com.zaneta.japantrip.mapper.user;

import com.zaneta.japantrip.model.User;
import com.zaneta.japantrip.model.dto.user.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> user);
}
