package com.training.fitnessappserver.repository;

import com.training.fitnessappserver.entity.Goal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface GoalRepository extends MongoRepository<Goal, String> {
    List<Goal> findByUserId(String userId);
}
