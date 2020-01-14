package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.service.ActivityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Override
    public List<Activity> getDailyActivities(String userId, LocalDate date) {
        return Arrays.asList(
                new Activity("Утрення активность", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Завтрак", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Работа1", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Обед", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Работа2", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Полдник", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Работа3", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Ужин", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Сон", "Пробежка 10 минут", false, LocalDate.now())
        );
    }
}
