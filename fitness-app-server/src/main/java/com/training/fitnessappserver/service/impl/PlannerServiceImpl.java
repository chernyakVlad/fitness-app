package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.ActivityRepository;
import com.training.fitnessappserver.service.PlannerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class PlannerServiceImpl implements PlannerService {
    ActivityRepository activityRepository;

    @Override
    public List<Activity> getDailyActivities(String userId, LocalDate date) {

        List<Activity> activity = activityRepository.getActivitiesByUserIdAndDate(userId, date);
        if (activity == null) {
            throw new ItemNotFoundException("There is no activity on date" + date + "and userId" + userId);
        } else {
            return activity;
        }
    }
}
