package com.hbtn.zafirasolidaire.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hbtn.zafirasolidaire.model.ServicedUser;

@Repository
public interface ServicedUserRepository extends CrudRepository<ServicedUser, UUID>{
    public void deleteServicedUserByUserId(UUID userId);
}
