package com.training.fitnessappserver.service;

import com.training.fitnessappserver.entity.motivation.MotivationItem;

public interface MotivationItemService {
    MotivationItem update(String motivationItemId, MotivationItem motivationItem);

    MotivationItem addMotivationItem(MotivationItem motivationItem);

    MotivationItem getById(String motivationItemId);

}
