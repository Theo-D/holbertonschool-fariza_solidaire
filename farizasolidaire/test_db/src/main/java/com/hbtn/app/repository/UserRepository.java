package com.hbtn.test_db.repository;

import org.springframework.data.repository.CrudRepository;

import com.hbtn.test_db.model.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {

}
