package com.hbtn.zafirasolidaire.repo;

import org.springframework.data.repository.CrudRepository;

import com.hbtn.zafirasolidaire.model.User;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepo extends CrudRepository<User, Integer> {

}
