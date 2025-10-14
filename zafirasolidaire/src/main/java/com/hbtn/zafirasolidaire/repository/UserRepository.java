package com.hbtn.zafirasolidaire.repository;

import com.hbtn.zafirasolidaire.model.User;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByEmailAddress(String email);

}
