package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.motivation.Motivation;

public interface MotivationService {
    Motivation update(String motivationId, Motivation motivation);

    Motivation save(Motivation motivation);

    Motivation getById(String motivationId);

}
