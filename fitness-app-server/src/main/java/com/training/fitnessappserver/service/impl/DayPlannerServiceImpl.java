package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.DayPlanner;
import com.training.fitnessappserver.entity.User;
import com.training.fitnessappserver.repository.DayPlannerRepository;
import com.training.fitnessappserver.repository.UserRepository;
import com.training.fitnessappserver.service.ActivityService;
import com.training.fitnessappserver.service.DayPlannerService;
import com.training.fitnessappserver.service.UserService;

import java.util.List;

public class DayPlannerServiceImpl implements DayPlannerService {
    UserRepository userRepository;
    DayPlannerRepository dayPlannerRepository;

    @Override
    public List<Activity> getPlansForToday(String userId) {
        return null;
    }
}
