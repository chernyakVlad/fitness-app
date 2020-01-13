package com.training.fitnessappserver.services.exercise;

import com.training.fitnessappserver.entity.enums.GoalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceFactory {
    private ExerciseService exerciseForGainService;
    private ExerciseService exerciseForHoldService;
    private ExerciseService exerciseForLoseService;

    @Autowired
    public ExerciseServiceFactory(ExerciseService exerciseForGainService,
                                  ExerciseService exerciseForHoldService,
                                  ExerciseService exerciseForLoseService) {
        this.exerciseForGainService = exerciseForGainService;
        this.exerciseForHoldService = exerciseForHoldService;
        this.exerciseForLoseService = exerciseForLoseService;
    }

    public ExerciseService getExerciseService(GoalType goalType) {
        switch (goalType) {
            case GAIN:
                return this.exerciseForGainService;
            case HOLD:
                return this.exerciseForHoldService;
            case LOSE:
                return this.exerciseForLoseService;
            default:
                //fix this!!!
                return null;
        }
    }
}
