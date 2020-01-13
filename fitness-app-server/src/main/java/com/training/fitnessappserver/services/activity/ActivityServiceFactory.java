package com.training.fitnessappserver.services.activity;

import com.training.fitnessappserver.entity.enums.GoalType;
import com.training.fitnessappserver.services.activity.impl.ActivityForGainService;
import com.training.fitnessappserver.services.activity.impl.ActivityForHoldService;
import com.training.fitnessappserver.services.activity.impl.ActivityForLoseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceFactory {
    private ActivityService activityForGainService;
    private ActivityService activityForHoldService;
    private ActivityService activityForLoseService;

    @Autowired
    public ActivityServiceFactory(ActivityForGainService activityForGainService,
                                  ActivityForHoldService activityForHoldService,
                                  ActivityForLoseService activityForLoseService) {
        this.activityForGainService = activityForGainService;
        this.activityForHoldService = activityForHoldService;
        this.activityForLoseService = activityForLoseService;
    }

    public ActivityService getActivityService(GoalType goalType) {
        switch (goalType) {
            case GAIN:
                return this.activityForGainService;
            case HOLD:
                return this.activityForHoldService;
            case LOSE:
                return this.activityForLoseService;
            default:
                //fix this!!!!
                return null;
        }
    }
}
