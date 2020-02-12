package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.motivation.MotivationItem;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.MotivationItemRepository;
import com.training.fitnessappserver.service.MotivationItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotivationItemServiceImpl implements MotivationItemService {

    MotivationItemRepository motivationItemRepository;

    @Autowired
    public MotivationItemServiceImpl(MotivationItemRepository motivationItemRepository) {
        this.motivationItemRepository = motivationItemRepository;
    }

    @Override
    public MotivationItem update(String motivationItemId, MotivationItem motivationItem) {
        MotivationItem updateMotivationItemId = getById(motivationItemId);
        BeanUtils.copyProperties(motivationItem, updateMotivationItemId, "newsId");
        return motivationItemRepository.save(updateMotivationItemId);
    }

    @Override
    public MotivationItem addMotivationItem(MotivationItem motivationItem) {
        return motivationItemRepository.insert(motivationItem);
    }

    @Override
    public MotivationItem getById(String motivationItemId) {
        return motivationItemRepository.findById(motivationItemId)
                .orElseThrow(() -> new ItemNotFoundException("There is no news " + "with newsId" + motivationItemId));
    }
    @Override
    public void delete(String motivationItemId) {
        MotivationItem motivationItem = getById(motivationItemId);
        motivationItemRepository.delete(motivationItem);
    }
}
