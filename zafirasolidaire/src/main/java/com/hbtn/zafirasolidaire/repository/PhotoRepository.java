package com.hbtn.zafirasolidaire.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hbtn.zafirasolidaire.model.Photo;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, UUID>{
    @Query("SELECT p FROM Photo p LEFT JOIN FETCH p.user LEFT JOIN FETCH p.event LEFT JOIN FETCH p.blogPost WHERE p.id = :id")
    Optional<Photo> findByIdWithAssociations(@Param("id") UUID id);
}
