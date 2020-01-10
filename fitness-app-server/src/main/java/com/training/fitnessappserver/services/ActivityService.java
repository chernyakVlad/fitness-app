package com.training.fitnessappserver.services;

import com.training.fitnessappserver.entity.Activity;

import java.time.LocalDate;
import java.util.List;

public interface ActivityService {
    List<Activity> getDailyActivities (String userId, LocalDate date);
}
