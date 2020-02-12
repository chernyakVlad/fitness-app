package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.enums.GoalType;
import com.training.fitnessappserver.entity.motivation.Motivation;
import com.training.fitnessappserver.entity.motivation.MotivationItem;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.MotivationRepository;
import com.training.fitnessappserver.service.MotivationItemService;
import com.training.fitnessappserver.service.MotivationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotivationServiceImpl implements MotivationService {
    MotivationRepository motivationRepository;
    MotivationItemService motivationItemService;

    @Autowired
    public MotivationServiceImpl(MotivationRepository motivationRepository, MotivationItemService motivationItemService) {
        this.motivationRepository = motivationRepository;
        this.motivationItemService = motivationItemService;
    }

    @Override
    public MotivationItem addMotivationItem(String motivationId, MotivationItem motivationItem) {
        Motivation motivation = getMotivationById(motivationId);
        MotivationItem motivationItem1 = motivationItemService.addMotivationItem(motivationItem);
        motivation.getMotivationItems().add(motivationItem1);
        update(motivationId, motivation);
        return motivationItem1;
    }


    @Override
    public Motivation getMotivationById(String motivationId) {
        return motivationRepository.findById(motivationId)
                .orElseThrow(() -> new ItemNotFoundException("motivation with id - " + motivationId + "not found"));
    }

    @Override
    public Motivation update(String motivationId, Motivation motivation) {
        Motivation uMotivation = getMotivationById(motivationId);
        BeanUtils.copyProperties(motivation, uMotivation, "motivationId");
        return motivationRepository.save(uMotivation);
    }

    @Override
    public Motivation addMotivation(Motivation motivation) {
        if (motivation != null) {
            return motivationRepository.insert(motivation);
        } else {
            throw new ItemNotFoundException("There is no motivation to save");
        }
    }

    Motivation initialMotivation(String userId) {
        Motivation motivation = new Motivation(userId, GoalType.HOLD);
        return addMotivation(motivation);

    }

    @Override
    public Motivation getMotivationByUserId(String userId) {
        return motivationRepository.getMotivationByUserId(userId)
                .orElseGet(() -> initialMotivation(userId));

    }

    @Override
    public void deleteMotivationItem(String motivationId, String motivationItemId) {
        Motivation motivation = getMotivationById(motivationId);
        motivation.getMotivationItems().remove(motivationItemService.getById(motivationItemId));
        motivationItemService.delete(motivationItemId);
    }
}
