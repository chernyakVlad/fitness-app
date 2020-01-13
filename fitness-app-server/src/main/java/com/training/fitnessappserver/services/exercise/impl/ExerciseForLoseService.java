package com.training.fitnessappserver.services.exercise.impl;

import com.training.fitnessappserver.entity.Exercise;
import com.training.fitnessappserver.services.exercise.ExerciseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseForLoseService implements ExerciseService {
    @Override
    public Exercise getExerciseForToday(String userId) {
        return null;
    }

    @Override
    public List<Exercise> getExerciseForMonth(String userId) {
        return null;
    }
}
