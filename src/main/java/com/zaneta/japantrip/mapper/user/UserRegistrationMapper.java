package com.zaneta.japantrip.mapper.user;

import com.zaneta.japantrip.model.User;
import com.zaneta.japantrip.model.dto.user.UserRegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRegistrationMapper {

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "userId", ignore = true)
    User toUser(UserRegistrationRequest userRegistrationRequest);
}
