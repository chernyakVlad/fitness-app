package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.motivation.MotivationItem;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.MotivationItemIdRepository;
import com.training.fitnessappserver.service.MotivationItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotivationItemServiceImpl implements MotivationItemService {

    MotivationItemIdRepository motivationItemIdRepository;

    @Autowired
    public MotivationItemServiceImpl(MotivationItemIdRepository motivationItemIdRepository) {
        this.motivationItemIdRepository = motivationItemIdRepository;
    }

    @Override
    public MotivationItem update(String motivationItemId, MotivationItem motivationItem) {
        MotivationItem updateMotivationItemId = getById(motivationItemId);
        BeanUtils.copyProperties(motivationItem, updateMotivationItemId, "newsId");
        return motivationItemIdRepository.save(updateMotivationItemId);
    }

    @Override
    public MotivationItem addMotivationItem(MotivationItem motivationItem) {
        return motivationItemIdRepository.insert(motivationItem);
    }

    @Override
    public MotivationItem getById(String motivationItemId) {
        return motivationItemIdRepository.findById(motivationItemId)
                .orElseThrow(() -> new ItemNotFoundException("There is no news " + "with newsId" + motivationItemId));
    }
}
