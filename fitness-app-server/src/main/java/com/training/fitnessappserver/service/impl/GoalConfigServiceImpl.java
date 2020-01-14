package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.GoalConfig;
import com.training.fitnessappserver.entity.enums.GoalType;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.GoalConfigRepository;
import com.training.fitnessappserver.repository.GoalRepository;
import com.training.fitnessappserver.service.GoalConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalConfigServiceImpl implements GoalConfigService {

    GoalConfigRepository goalConfigRepository;

    @Autowired
    public GoalConfigServiceImpl(GoalConfigRepository goalConfigRepository) {
        this.goalConfigRepository = goalConfigRepository;
    }

    @Override
    public GoalConfig get(GoalType type) {
        return goalConfigRepository.findByGoalType(type)
                .orElseThrow(() -> new ItemNotFoundException("Goal config for goal with type -  " + type.name() + " not found"));
    }

    @Override
    public GoalConfig save(GoalConfig config) {
        return goalConfigRepository.insert(config);
    }

    @Override
    public GoalConfig update(GoalConfig config) {
        GoalConfig newConfig = get(config.getGoalType());
        BeanUtils.copyProperties(config, newConfig, "id", "goalType" );
        return goalConfigRepository.save(newConfig);
    }
}
