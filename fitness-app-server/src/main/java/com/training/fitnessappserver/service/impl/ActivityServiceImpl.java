package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.User;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.ActivityRepository;
import com.training.fitnessappserver.repository.UserRepository;
import com.training.fitnessappserver.service.ActivityService;
import com.training.fitnessappserver.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {

    ActivityRepository activityRepository;

    @Override
    public List<Activity> getActivitiesByDateAndUserId(String userId, LocalDate date) {

        Activity activity = activityRepository.getActivityByUserIdAndDate(userId, date);
        List<Activity> activities=new ArrayList<>();
        if (activity == null) {
            throw new ItemNotFoundException("There is no activity on date" + date + "and userId" + userId);
        } else {
            activities.add(activity);

            return activities;
        }
    }
}
