package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.enums.GoalType;
import com.training.fitnessappserver.entity.goal.GoalConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface GoalConfigRepository extends MongoRepository<GoalConfig, String> {
    Optional<GoalConfig> findByGoalType(GoalType goalType);
}
