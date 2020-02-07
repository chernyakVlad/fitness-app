package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.motivation.Motivation;
import com.training.fitnessappserver.entity.motivation.MotivationItem;

public interface MotivationService {
    Motivation update(String motivationId, Motivation motivation);

    Motivation addMotivation(Motivation motivation);

    Motivation getMotivationById(String motivationId);

    Motivation getMotivationByUserId(String userId);

    Motivation addMotivationItem(String motivationId, MotivationItem motivationItem);

}
