package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ActivityRepository extends MongoRepository<Activity, String> {

    Optional<List<Activity>> getActivitiesByPlanId(String planId);
}
