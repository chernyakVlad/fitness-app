package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.Exercise;
import com.training.fitnessappserver.entity.GoalConfig;
import com.training.fitnessappserver.entity.User;
import com.training.fitnessappserver.entity.enums.GoalType;
import com.training.fitnessappserver.service.ExerciseService;
import com.training.fitnessappserver.service.GoalConfigService;
import com.training.fitnessappserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    UserService userService;
    ExerciseStoreService exerciseStoreService;
    GoalConfigService goalConfigService;

    @Autowired
    public ExerciseServiceImpl(UserServiceImpl userService, ExerciseStoreService exerciseStoreService, GoalConfigServiceImpl goalConfigService) {
        this.userService = userService;
        this.goalConfigService = goalConfigService;
        this.exerciseStoreService = exerciseStoreService;
    }

    @Override
    public Exercise getExerciseForToday(String userId) {
        User user = userService.findById(userId);
        GoalConfig goalConfig = goalConfigService.get(GoalType.LOSE);
        LocalDate date = LocalDate.now();
        List<Exercise> exercise = exerciseStoreService.getByUserIdAndDate(userId, date);
        if(exercise == null || exercise.size() <= 0) {
            int calories = (int) (66.5 + goalConfig.getWeightRatioForCalories() * user.getWeight() + goalConfig.getHeightRatioForCalories() * user.getHeight() - goalConfig.getAgeRatioForCalories() * user.getAge());
            double water = goalConfig.getWeightRatioForWater() * user.getWeight();
            Exercise exercise1 = new Exercise(calories, water, goalConfig.getSleepInHours(), goalConfig.getActivity(), date, userId);
            return exerciseStoreService.save(exercise1);
        } else {
            return exercise.get(0);
        }
    }

    @Override
    public Exercise update(String id, Exercise exercise) {
        return exerciseStoreService.update(id, exercise);
    }
}
