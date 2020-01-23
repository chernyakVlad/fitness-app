package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.Exercise;

public interface ExerciseService {
    Exercise getExerciseForToday(String userId);
    Exercise update(String id, Exercise exercise);
    Exercise save(Exercise exercise);
}
