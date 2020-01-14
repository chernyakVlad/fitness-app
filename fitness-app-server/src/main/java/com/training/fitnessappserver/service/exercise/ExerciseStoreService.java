package com.training.fitnessappserver.service.exercise;

import com.training.fitnessappserver.entity.Exercise;
import com.training.fitnessappserver.repository.ExerciseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExerciseStoreService {

    ExerciseRepository exerciseRepository;

    @Autowired
    public ExerciseStoreService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getByUserIdAndDate(String userId, LocalDate date) {
        return exerciseRepository.getByUserIdAndDate(userId, date);
    }

    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    public Exercise update(String exerciseId, Exercise exercise) {
        Exercise updatedExercise = exerciseRepository.findById(exerciseId).get();
        BeanUtils.copyProperties(exercise, updatedExercise, "id");
        return exerciseRepository.save(updatedExercise);
    }
}
