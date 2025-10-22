package com.hbtn.zafirasolidaire.repository;

import com.hbtn.zafirasolidaire.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {

    Optional<User> findByEmailAddress(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.profilePic")
    List<User> findAllWithProfilePic();


}
