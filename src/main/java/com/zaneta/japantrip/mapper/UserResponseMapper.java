package com.zaneta.japantrip.mapper;

import com.zaneta.japantrip.model.User;
import com.zaneta.japantrip.model.dto.user.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {

    //UserResponseMapper INSTANCE = Mappers.getMapper(UserResponseMapper.class);

    UserResponse mapToUserResponse(User user);

    User mapToUser(UserResponse userResponse);


}
