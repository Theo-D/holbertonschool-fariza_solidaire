package com.hbtn.zafirasolidaire.mapper;

import com.hbtn.zafirasolidaire.dto.ServicedUserDto;
import com.hbtn.zafirasolidaire.model.ServicedUser;

public class ServicedUserMapper {

    ServicedUserDto servicedUserToDto(ServicedUser servicedUser){
        ServicedUserDto servicedUserDto = new ServicedUserDto();

        servicedUserDto.setId(servicedUser.getId());
        servicedUserDto.setUserId(servicedUser.getUser().getId());

        return servicedUserDto;
    };

    ServicedUser dtoToServicedUser(ServicedUserDto servicedUserDto) {
        ServicedUser servicedUser = new ServicedUser();

        servicedUser.getUser().setId(servicedUserDto.getId());

        return servicedUser;

    }
}
