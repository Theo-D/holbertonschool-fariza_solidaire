package com.hbtn.zafirasolidaire.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hbtn.zafirasolidaire.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, UUID>{
    boolean existsByCategoryId(UUID categoryId);
}
