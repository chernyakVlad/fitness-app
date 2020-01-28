package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.exercise.Exercise;
import com.training.fitnessappserver.entity.goal.GoalConfig;
import com.training.fitnessappserver.entity.user.User;
import com.training.fitnessappserver.entity.enums.GoalType;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.ExerciseRepository;
import com.training.fitnessappserver.service.ExerciseService;
import com.training.fitnessappserver.service.GoalConfigService;
import com.training.fitnessappserver.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    UserService userService;
    GoalConfigService goalConfigService;
    ExerciseRepository exerciseRepository;


    public List<Exercise> getByUserIdAndDate(String userId, LocalDate date) {
        return exerciseRepository.getByUserIdAndDate(userId, date);
    }


    @Override
    public Exercise update(String exerciseId, Exercise exercise) {
        Optional<Exercise> updatedExercise = exerciseRepository.findById(exerciseId);
        if (updatedExercise.isPresent()) {
            BeanUtils.copyProperties(exercise, updatedExercise, "id");
            return exerciseRepository.save(updatedExercise.get());
        } else {
            throw new ItemNotFoundException("Exercise not found");
        }
    }
    @Autowired
    public ExerciseServiceImpl(UserService userService, GoalConfigService goalConfigService, ExerciseRepository exerciseRepository) {
        this.userService = userService;
        this.goalConfigService = goalConfigService;
        this.exerciseRepository = exerciseRepository;
    }




    @Override
    public Exercise getExerciseForToday(String userId) {
        User user = userService.findById(userId);
        GoalConfig goalConfig = goalConfigService.get(GoalType.LOSE);
        LocalDate date = LocalDate.now();
        List<Exercise> exercise = getByUserIdAndDate(userId, date);
        if (exercise == null || exercise.size() <= 0) {
            int calories = (int) (66.5 + goalConfig.getWeightRatioForCalories() * user.getWeight() + goalConfig.getHeightRatioForCalories() * user.getHeight() - goalConfig.getAgeRatioForCalories() * user.getAge());
            double water = goalConfig.getWeightRatioForWater() * user.getWeight();
            Exercise exercise1 = new Exercise(calories, water, goalConfig.getSleepInHours(), goalConfig.getActivity(), date, userId);
            return save(exercise1);

        } else {
            return exercise.get(0);

        }
    }

    @Override
    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }
}
