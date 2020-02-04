package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.plan.Activity;
import com.training.fitnessappserver.entity.plan.Plan;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface PlanService {
    Set<Activity> getActivitiesForDay(String planId);

    Plan getById(String planId);

    Plan getByUserId(String userId);

    Plan getPlanByUserIdAndDate(String userId, LocalDate date);

    Plan save(Plan plan);

    Plan update(String planId, Plan plan);

    Activity addPlanActivity(String planId, Activity activity);
}
