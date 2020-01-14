package com.training.fitnessappserver.service.exercise.impl;

import com.training.fitnessappserver.entity.Exercise;
import com.training.fitnessappserver.entity.GoalConfig;
import com.training.fitnessappserver.entity.User;
import com.training.fitnessappserver.entity.enums.GoalType;
import com.training.fitnessappserver.service.GoalConfigService;
import com.training.fitnessappserver.service.UserService;
import com.training.fitnessappserver.service.exercise.ExerciseService;
import com.training.fitnessappserver.service.exercise.ExerciseStoreService;
import com.training.fitnessappserver.service.impl.GoalConfigServiceImpl;
import com.training.fitnessappserver.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExerciseForGainService implements ExerciseService {

    UserService userService;
    ExerciseStoreService exerciseStoreService;
    GoalConfigService goalConfigService;

    @Autowired
    public ExerciseForGainService(UserServiceImpl userService, ExerciseStoreService exerciseStoreService, GoalConfigServiceImpl goalConfigService) {
        this.userService = userService;
        this.goalConfigService = goalConfigService;
        this.exerciseStoreService = exerciseStoreService;
    }

    @Override
    public Exercise getExerciseForToday(String userId) {
        User user = userService.findById(userId);
        GoalConfig goalConfig = goalConfigService.get(GoalType.GAIN);
        List<Exercise> exercise = exerciseStoreService.getByUserIdAndDate(userId, LocalDate.now());
        if (exercise == null || exercise.size() <= 0) {
            int calories = (int) (66.5 + goalConfig.getWeightRatioForCalories() * user.getWeight() + goalConfig.getHeightRatioForCalories() * user.getHeight() - goalConfig.getAgeRatioForCalories() * user.getAge());
            double water = goalConfig.getWeightRatioForWater() * user.getWeight();
            Exercise exercise1 = new Exercise(calories, water, goalConfig.getSleepInHours(), goalConfig.getActivity(), LocalDate.now(), userId);
            return exerciseStoreService.save(exercise1);
        } else {
            return exercise.get(0);
        }
    }
}
