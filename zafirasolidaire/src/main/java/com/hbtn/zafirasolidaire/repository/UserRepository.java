package com.hbtn.zafirasolidaire.repository;

import com.hbtn.zafirasolidaire.model.User;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, UUID> {

}
