package com.training.fitnessappserver.services.exercise;

import com.training.fitnessappserver.entity.Exercise;

import java.util.List;

public interface ExerciseService {
    Exercise getExerciseForToday(String userId);
    List<Exercise> getExerciseForMonth(String userId);
}
