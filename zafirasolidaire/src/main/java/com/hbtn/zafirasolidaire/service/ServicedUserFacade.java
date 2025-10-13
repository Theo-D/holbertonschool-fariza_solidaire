package com.hbtn.zafirasolidaire.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbtn.zafirasolidaire.dto.ServicedUserDto;
import com.hbtn.zafirasolidaire.mapper.ServicedUserMapper;
import com.hbtn.zafirasolidaire.model.ServicedUser;
import com.hbtn.zafirasolidaire.repository.ServicedUserRepository;

@Service
public class ServicedUserFacade {
    private final ServicedUserMapper servicedUserMapper;
    private final ServicedUserRepository servicedUserRepository;

    @Autowired
    public ServicedUserFacade(ServicedUserMapper servicedUserMapper, ServicedUserRepository servicedUserRepository) {
        this.servicedUserMapper = servicedUserMapper;
        this.servicedUserRepository = servicedUserRepository;
    }

    //---------- Repository Services ----------//
    public void createServicedUser(ServicedUserDto ServicedUserDto) {
        if (ServicedUserDto == null) {
            throw new IllegalArgumentException("ServicedUser cannot be null.");
        }

        ServicedUser ServicedUser = servicedUserMapper.dtoToServicedUser(ServicedUserDto);

        servicedUserRepository.save(ServicedUser);
    }

    public void createAllServicedUsers(Iterable<ServicedUserDto> ServicedUserDtos) {
        if (ServicedUserDtos == null || !ServicedUserDtos.iterator().hasNext()) {
            throw new IllegalArgumentException("ServicedUser list cannot be null or empty.");
        }

        List<ServicedUser> ServicedUsers = new ArrayList<>();

        for (ServicedUserDto ServicedUserDto : ServicedUserDtos) {
            ServicedUser ServicedUser = servicedUserMapper.dtoToServicedUser(ServicedUserDto);
            ServicedUsers.add(ServicedUser);
        }
        servicedUserRepository.saveAll(ServicedUsers);
    }

    public ServicedUserDto getServicedUserById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ServicedUser ID cannot be null.");
        }

        ServicedUser foundServicedUser = servicedUserRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));

        return servicedUserMapper.servicedUserToDto(foundServicedUser);
    }

    public boolean existsById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ServicedUser ID cannot be null.");
        }
        return servicedUserRepository.existsById(id);
    }

    public Iterable<ServicedUserDto> getAllServicedUsers() {
        return mapToDto(servicedUserRepository.findAll());
    }

    public Iterable<ServicedUserDto> getAllServicedUsersById(Iterable<UUID> ids) {
        if (ids == null || !ids.iterator().hasNext()) {
            throw new IllegalArgumentException("ID list cannot be null or empty.");
        }

        return mapToDto(servicedUserRepository.findAllById(ids));
    }

    public void deleteServicedUserById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ServicedUser ID cannot be null.");
        }
        servicedUserRepository.deleteById(id);
    }

    public void deleteServicedUser(ServicedUser ServicedUser) {
        if (ServicedUser == null) {
            throw new IllegalArgumentException("ServicedUser cannot be null.");
        }
        servicedUserRepository.delete(ServicedUser);
    }

    public void deleteAllServicedUsers() {
        servicedUserRepository.deleteAll();
    }

    public void deleteAllServicedUsers(Iterable<ServicedUser> ServicedUsers) {
        if (ServicedUsers == null || !ServicedUsers.iterator().hasNext()) {
            throw new IllegalArgumentException("ServicedUser list cannot be null or empty.");
        }
        servicedUserRepository.deleteAll(ServicedUsers);
    }

    public long countServicedUsers() {
        return servicedUserRepository.count();
    }

    //--------- Helper methods ---------//

    private Iterable<ServicedUserDto> mapToDto(Iterable<ServicedUser> ServicedUsers) {
        return StreamSupport.stream(ServicedUsers.spliterator(), false)
            .map(servicedUserMapper::servicedUserToDto)
            .collect(Collectors.toList());
    }
}
