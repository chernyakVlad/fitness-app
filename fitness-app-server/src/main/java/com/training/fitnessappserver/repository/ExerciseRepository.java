package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.exercise.Exercise;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface ExerciseRepository extends MongoRepository<Exercise, String> {
    List<Exercise> getByUserIdAndDate(String userId, LocalDate date);
}
