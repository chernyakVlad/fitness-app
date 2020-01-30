package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.plan.Activity;
import com.training.fitnessappserver.entity.plan.Plan;

import java.time.LocalDate;
import java.util.List;

public interface PlanService {
    List<Activity> getActivitiesForDay(String planId);

    Plan getById(String planId);

    Plan getByUserId(String userId);

    Plan getPlanByUserIdAndDate(String userId, LocalDate date);

    Plan save(Plan plan);

    Plan update(String planId, Plan plan);

    Plan addPlanActivity(String planId, Activity activity);
}
