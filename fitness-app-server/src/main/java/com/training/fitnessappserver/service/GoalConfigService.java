package com.training.fitnessappserver.service;


import com.training.fitnessappserver.entity.GoalConfig;
import com.training.fitnessappserver.entity.enums.GoalType;

public interface GoalConfigService {
    GoalConfig get(GoalType type);
    GoalConfig save(GoalConfig config);
    GoalConfig update(GoalConfig config);
}
