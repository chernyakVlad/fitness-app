package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.Plan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlanRepository extends MongoRepository<Plan, String> {
    Optional<Plan> getPlanActivitiesByUserIdAndDate(String userId, LocalDate date);
    Optional<Plan> getPlanByUserIdAndDate(String userId, LocalDate date);
    List<Plan> findByUserId(String userId);
}
