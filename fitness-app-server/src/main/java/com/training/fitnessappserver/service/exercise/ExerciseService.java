package com.training.fitnessappserver.service.exercise;

import com.training.fitnessappserver.entity.Exercise;

public interface ExerciseService {
    Exercise getExerciseForToday(String userId);
}
