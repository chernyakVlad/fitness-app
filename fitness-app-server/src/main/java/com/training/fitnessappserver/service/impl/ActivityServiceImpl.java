package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.plan.Activity;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.ActivityRepository;
import com.training.fitnessappserver.service.ActivityService;
import com.training.fitnessappserver.service.PlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    ActivityRepository activityRepository;
    PlanService planService;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, PlanService planService) {
        this.activityRepository = activityRepository;
        this.planService = planService;
    }





    @Override
    public Activity getById(String activityId) {
        return activityRepository.findById(activityId)
                .orElseThrow(() -> new ItemNotFoundException("There is no activity " + "with activityId" + activityId));
    }

    @Override
    public Activity update(String activityId, Activity activity) {

            Activity updateActivity = getById(activityId);
            BeanUtils.copyProperties(activity, updateActivity, "activityId");
            return save(updateActivity);
    }

    @Override
    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }
    @Override
    public Activity addActivity(String planId,Activity activity) {
        planService.addPlanActivity(planId,activity);
        return save(activity);
    }
}
