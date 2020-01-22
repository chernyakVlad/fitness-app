package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.ActivityRepository;
import com.training.fitnessappserver.repository.PlanRepository;
import com.training.fitnessappserver.service.PlanService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class PlanServiceImpl implements PlanService {
    PlanRepository planRepository;

    @Override
    public List<Activity> getDailyActivities(String userId, LocalDate date) {

        List<Activity> activity = planRepository.getPlanActivitiesByUserIdAndDate(userId, date).getActivities();
        if (activity == null) {
            throw new ItemNotFoundException("There is no activity on date" + date + "and userId" + userId);
        } else {
            return activity;
        }
    }
}
