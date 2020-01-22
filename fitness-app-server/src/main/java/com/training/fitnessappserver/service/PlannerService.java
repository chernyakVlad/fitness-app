package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.Activity;

import java.time.LocalDate;
import java.util.List;

public interface PlannerService {
    List<Activity> getDailyActivities(String userId, LocalDate date);
}
