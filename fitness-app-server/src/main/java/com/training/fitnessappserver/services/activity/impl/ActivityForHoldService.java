package com.training.fitnessappserver.services.activity.impl;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.services.activity.ActivityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class ActivityForHoldService implements ActivityService {
    @Override
    public List<Activity> getDailyActivities(String userId, LocalDate date) {
        return Arrays.asList(
                new Activity("HOLD_Утрення активность", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("HOLD_Завтрак", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("HOLD_Работа1", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("HOLD_Обед", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("HOLD_Работа2", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("HOLD_Полдник", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("HOLD_Работа3", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("HOLD_Ужин", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("HOLD_Сон", "Пробежка 10 минут", false, LocalDate.now())
        );
    }
}
