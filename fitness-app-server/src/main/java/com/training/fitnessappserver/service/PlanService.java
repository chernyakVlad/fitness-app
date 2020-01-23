package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.Plan;

import java.time.LocalDate;
import java.util.List;

public interface PlanService {
    List<Activity> getActivitiesForDay(String planId, LocalDate date);
    Plan getById(String planId);
    Plan getPlan(String userId, LocalDate date);
    Plan save(Plan plan);
    Plan update(String id, Plan plan);
}
