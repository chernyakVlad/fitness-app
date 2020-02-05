package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.motivation.Motivation;
import com.training.fitnessappserver.entity.motivation.MotivationItem;

public interface MotivationService {
    Motivation update(String motivationId, Motivation motivation);

    Motivation addMotivation(Motivation motivation);

    Motivation getById(String motivationId);

    Motivation addMotivationNews(String motivationId, MotivationItem motivationItem);

}
