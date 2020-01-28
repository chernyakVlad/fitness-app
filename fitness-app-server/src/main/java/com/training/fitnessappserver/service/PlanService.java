package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.plan.Activity;
import com.training.fitnessappserver.entity.plan.Plan;

import java.time.LocalDate;
import java.util.List;

public interface PlanService {
    List<Activity> getActivitiesForDay(String planId);

    Plan getById(String planId);

    Plan getByUserId(String userId);

    Plan getPlan(String userId, LocalDate date);

    Plan save(Plan plan);

    Plan update(String id, Plan plan);

    Plan addPlanActivity(String id, Activity activity);
}
