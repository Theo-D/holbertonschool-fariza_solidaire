package com.hbtn.zafirasolidaire.mapper;


import org.springframework.stereotype.Component;

import com.hbtn.zafirasolidaire.dto.ServicedUserDto;
import com.hbtn.zafirasolidaire.model.ServicedUser;
import com.hbtn.zafirasolidaire.model.User;

@Component
public class ServicedUserMapper {

    public ServicedUserDto servicedUserToDto(ServicedUser servicedUser){
        ServicedUserDto servicedUserDto = new ServicedUserDto();

        servicedUserDto.setId(servicedUser.getId());
        servicedUserDto.setUserId(servicedUser.getUser().getId());

        return servicedUserDto;
    };

    public ServicedUser dtoToServicedUser(ServicedUserDto servicedUserDto) {
        ServicedUser servicedUser = new ServicedUser();
        User user = new User().setId(servicedUserDto.getUserId());

        servicedUser.setUser(user);
        servicedUser.setId(servicedUserDto.getId());

        return servicedUser;
    }
}
