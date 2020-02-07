package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.motivation.Motivation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MotivationRepository extends MongoRepository<Motivation, String> {
    Optional<Motivation> getMotivationByUserId(String userId);
}
