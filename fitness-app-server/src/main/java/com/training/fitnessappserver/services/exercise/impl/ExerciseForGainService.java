package com.training.fitnessappserver.services.exercise.impl;

import com.training.fitnessappserver.entity.Exercise;
import com.training.fitnessappserver.entity.User;
import com.training.fitnessappserver.services.UserService;
import com.training.fitnessappserver.services.exercise.ExerciseService;
import com.training.fitnessappserver.services.exercise.ExerciseStoreService;
import com.training.fitnessappserver.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ExerciseForGainService implements ExerciseService {

    UserService userService;
    ExerciseStoreService exerciseStoreService;

    @Autowired
    public ExerciseForGainService(UserServiceImpl userService, ExerciseStoreService exerciseStoreService) {
        this.userService = userService;
        this.exerciseStoreService = exerciseStoreService;
    }

    private static List<Exercise> exercises = Arrays.asList(
            new Exercise(2500, 1.5, 8, "two hours running"),
            new Exercise(2501, 1.6, 8, "two hours running"),
            new Exercise(2502, 1.7, 8, "two hours running"),
            new Exercise(2503, 1.8, 8, "two hours running")
    );

    @Override
    public Exercise getExerciseForToday(String userId) {
        User user = userService.findById(userId);
        LocalDate date = LocalDate.now();
        List<Exercise> exercise = exerciseStoreService.getByUserIdAndDate(userId, date);
        if(exercise == null) {
            int calories = (int) (66.5 + 13.75 * user.getWeight() + 5.003 * user.getHeight() - 6.775 * user.getAge());
            double water = 0.03 * user.getWeight();
            Exercise exercise1 = new Exercise(calories, water, 8, "two hours running", date, userId);
            return exerciseStoreService.save(exercise1);
        } else {
            return exercise.get(0);
        }
    }

    @Override
    public List<Exercise> getExerciseForMonth(String userId) {
        return exercises;
    }
}
