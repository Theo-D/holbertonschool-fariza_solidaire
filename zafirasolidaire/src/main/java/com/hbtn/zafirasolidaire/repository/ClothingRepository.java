package com.hbtn.zafirasolidaire.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hbtn.zafirasolidaire.model.Clothing;

@Repository
public interface ClothingRepository extends CrudRepository<Clothing, UUID>{

}
