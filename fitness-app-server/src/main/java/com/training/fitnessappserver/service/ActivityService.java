package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.plan.Activity;

public interface ActivityService {

    Activity update(String id, Activity activity);

    Activity addActivity(Activity activity);

    Activity getById(String activityId);

    void delete(String activityId);
}
