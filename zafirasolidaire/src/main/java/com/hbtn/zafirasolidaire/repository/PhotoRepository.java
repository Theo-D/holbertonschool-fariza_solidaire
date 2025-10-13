package com.hbtn.zafirasolidaire.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hbtn.zafirasolidaire.model.Photo;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, UUID>{

}
