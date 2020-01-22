package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ActivityRepository extends MongoRepository<Activity, String> {
    List<Activity> getActivitiesByUserIdAndDate(String userId, LocalDate date);

    Activity getActivityByUserIdAndDate(String userId, LocalDate date);
}
