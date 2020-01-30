package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.plan.Activity;

import java.util.List;

public interface ActivityService {

    Activity update(String id, Activity activity);

    Activity save(Activity activity);

    Activity addActivity(String planId,Activity activity);

    Activity getById(String activityId);
}
