package com.hbtn.zafirasolidaire.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hbtn.zafirasolidaire.model.RefreshToken;

import jakarta.transaction.Transactional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, UUID>{

    public Optional<RefreshToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("DELETE FROM RefreshToken rt WHERE rt.user.id = :userId")
    void deleteByUserId(UUID userId);
}
