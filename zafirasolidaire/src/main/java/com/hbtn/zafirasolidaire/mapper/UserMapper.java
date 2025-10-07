package com.hbtn.zafirasolidaire.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.hbtn.zafirasolidaire.dto.UserDto;
import com.hbtn.zafirasolidaire.dto.UserRequest;
import com.hbtn.zafirasolidaire.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

        @Mapping(source = "profilePic.url", target = "photoUrl")
        @Mapping(source = "id", target = "userId")
        UserDto userToDto(User user);

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "isAdmin", ignore = true)
        @Mapping(target = "profilePic", ignore = true)
        User userRequestToUser(UserRequest userRequest);
        @AfterMapping
        default void setSystemFields(@MappingTarget User user) {
                user.setIsAdmin(false);
        }
}
