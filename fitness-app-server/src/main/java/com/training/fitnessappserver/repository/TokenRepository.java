package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.JwtToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TokenRepository extends MongoRepository<JwtToken, String> {
    Optional<JwtToken> findByAccessToken(String accessToken);

}
