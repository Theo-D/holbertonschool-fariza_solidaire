package com.hbtn.zafirasolidaire.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.hbtn.zafirasolidaire.model.EventCategory;

public interface EventCategoryRepository extends CrudRepository<EventCategory, UUID>{

}
