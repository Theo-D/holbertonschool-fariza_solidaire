package com.hbtn.zafirasolidaire.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.hbtn.zafirasolidaire.model.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, UUID>{
    public Optional<RefreshToken> findByToken(String token);
}
