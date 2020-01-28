package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.plan.Activity;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.ActivityRepository;
import com.training.fitnessappserver.service.ActivityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }


    @Override
    public List<Activity> getActivitiesByPlanId(String planId) {

        return activityRepository.getActivitiesByPlanId(planId)
                .orElseThrow(() -> new ItemNotFoundException("There is no activity on date" + "and planId" + planId));

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
}
