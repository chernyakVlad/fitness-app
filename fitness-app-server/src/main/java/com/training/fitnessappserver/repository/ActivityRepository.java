package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.plan.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends MongoRepository<Activity, String> {


}
