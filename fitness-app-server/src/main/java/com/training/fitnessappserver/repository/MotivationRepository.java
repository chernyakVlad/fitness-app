package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.motivation.Motivation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MotivationRepository extends MongoRepository<Motivation, String> {

}
