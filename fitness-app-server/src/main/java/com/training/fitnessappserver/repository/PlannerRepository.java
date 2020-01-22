package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.Planner;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlannerRepository extends MongoRepository<Planner, String> {
    List<Activity> getByUserIdAndDate(String userId, LocalDate date);
}
