package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.DayPlanner;

import java.util.List;

public interface DayPlannerService {
    List<Activity> getPlansForToday(String userId);
}
