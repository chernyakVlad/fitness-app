package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.plan.Activity;

public interface ActivityService {

    Activity update(String id, Activity activity);

    Activity save(Activity activity);

    Activity getById(String activityId);
}
