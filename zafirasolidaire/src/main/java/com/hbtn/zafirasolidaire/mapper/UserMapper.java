package com.hbtn.zafirasolidaire.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hbtn.zafirasolidaire.dto.UserDto;
import com.hbtn.zafirasolidaire.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

        @Mapping(source = "profilePic.url", target = "photoUrl")
        @Mapping(source = "id", target = "userId")
        UserDto usertoDto(User user);
}
