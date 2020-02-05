package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.plan.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity, String> {


}
