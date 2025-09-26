package com.zaneta.japantrip.mapper;

import com.zaneta.japantrip.model.User;
import com.zaneta.japantrip.model.dto.user.UserLoginRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserLoginRequestMapper {

    UserLoginRequest mapToUserLoginDto(User user);

    User mapToUser(UserLoginRequest userLoginRequest);



}

