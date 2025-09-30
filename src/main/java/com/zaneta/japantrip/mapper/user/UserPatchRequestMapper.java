package com.zaneta.japantrip.mapper.user;

import com.zaneta.japantrip.model.User;
import com.zaneta.japantrip.model.dto.user.UserPatchRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserPatchRequestMapper {

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUser(UserPatchRequest userPatchRequest, @MappingTarget User user);
}
