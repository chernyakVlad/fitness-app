package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.goal.Goal;

import java.util.List;

public interface GoalService {
    Goal getById(String id);
    List<Goal> getAll();
    List<Goal> getByUserId(String userId);
    Goal save(Goal goal);
    Goal update(String id, Goal goal);
    void delete(String id);
}
