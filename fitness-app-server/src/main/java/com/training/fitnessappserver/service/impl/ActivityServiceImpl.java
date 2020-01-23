package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.User;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.ActivityRepository;
import com.training.fitnessappserver.repository.UserRepository;
import com.training.fitnessappserver.service.ActivityService;
import com.training.fitnessappserver.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService {

    ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }



    @Override
    public List<Activity> getActivitiesByDateAndUserId(String planId, LocalDate date) {

        return activityRepository.getActivitiesByPlanIdAndDate(planId, date)
                .orElseThrow(() -> new ItemNotFoundException("There is no activity on date" + date + "and planId" + planId));

    }

    @Override
    public Activity update(String activityId, Activity activity) {
        Optional<Activity> activities = activityRepository.findById(activityId);
        if (activities.isPresent()) {
            Activity updateActivity = activities.get();
            BeanUtils.copyProperties(activity, updateActivity, "activityId");
            return save(updateActivity);
        } else {
            throw new ItemNotFoundException("Activity not found");
        }
    }
    @Override
    public Activity save(Activity activity) {
        return activityRepository.save(activity);
    }
}
