package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.DayPlanner;
import com.training.fitnessappserver.entity.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface DayPlannerRepository  extends MongoRepository<DayPlanner, String> {
    List<Activity> getByUserIdAndDate(String userId, LocalDate date);
}
