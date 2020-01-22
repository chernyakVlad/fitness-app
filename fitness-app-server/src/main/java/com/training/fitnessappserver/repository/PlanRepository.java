package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.Plan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends MongoRepository<Plan, String> {
    List<Activity> getByUserIdAndDate(String userId, LocalDate date);
}
