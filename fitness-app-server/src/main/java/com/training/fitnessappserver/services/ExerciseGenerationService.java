package com.training.fitnessappserver.services;

import com.training.fitnessappserver.entity.Exercise;

import java.util.List;

public interface ExerciseGenerationService {
    Exercise getExerciseForToday(String userId);
    List<Exercise> getExerciseForMonth(String userId);
}
