package com.zaneta.japantrip.mapper.user;

import com.zaneta.japantrip.model.User;
import com.zaneta.japantrip.model.dto.user.UserUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserUpdateRequestMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    User toUser(UserUpdateRequest userUpdateRequest);

}
