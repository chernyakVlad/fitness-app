package com.training.fitnessappserver.service;


import com.training.fitnessappserver.entity.enums.GoalType;
import com.training.fitnessappserver.entity.goal.GoalConfig;

public interface GoalConfigService {
    GoalConfig get(GoalType type);

    GoalConfig save(GoalConfig config);

    GoalConfig update(GoalConfig config);
}
