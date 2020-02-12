package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.motivation.MotivationItem;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MotivationItemRepository extends MongoRepository<MotivationItem, String> {

}
