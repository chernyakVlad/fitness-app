package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.plan.Activity;

import java.util.List;

public interface ActivityService {
    List<Activity> getActivitiesByPlanId(String userId);

    Activity update(String id, Activity activity);

    Activity save(Activity activity);

    Activity getById(String activityId);
}
