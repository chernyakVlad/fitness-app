package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.motivation.MotivationItem;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MotivationItemIdRepository extends MongoRepository<MotivationItem, String> {

}
