package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.Exercise;

import java.time.LocalDate;
import java.util.List;

public interface ActivityService {
    List<Activity> getActivitiesByPlanId(String userId);
    Activity update(String id, Activity activity);
    Activity save(Activity activity);
}
