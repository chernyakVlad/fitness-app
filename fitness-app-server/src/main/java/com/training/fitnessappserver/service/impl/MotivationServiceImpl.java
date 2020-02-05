package com.training.fitnessappserver.service.impl;

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
    public Motivation addMotivationNews(String motivationId, MotivationItem motivationItem) {
        Motivation motivation = getById(motivationId);
        MotivationItem newNews = motivationItemService.addMotivationItem(motivationItem);
        motivation.getNews().add(newNews);
        return update(motivationId, motivation);
    }


    @Override
    public Motivation getById(String motivationId) {
        return motivationRepository.findById(motivationId)
                .orElseThrow(() -> new ItemNotFoundException("motivation with id - " + motivationId + "not found"));
    }

    @Override
    public Motivation update(String motivationId, Motivation motivation) {
        Motivation uMotivation = getById(motivationId);
        BeanUtils.copyProperties(uMotivation, motivation, "motivationId");
        return motivationRepository.save(uMotivation);
    }

    @Override
    public Motivation addMotivation(Motivation motivation) {
        if (motivation != null) {
            return motivationRepository.save(motivation);
        } else {
            throw new ItemNotFoundException("There is no motivation to save");
        }
    }
}
