package com.training.fitnessappserver.services.activity.impl;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.services.activity.ActivityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class ActivityForLoseService implements ActivityService {
    @Override
    public List<Activity> getDailyActivities(String userId, LocalDate date) {
        return Arrays.asList(
                new Activity("LOSE_Утрення активность", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("LOSE_Завтрак", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("LOSE_Работа1", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("LOSE_Обед", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("LOSE_Работа2", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("LOSE_Полдник", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("LOSE_Работа3", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("LOSE_Ужин", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("LOSE_Сон", "Пробежка 10 минут", false, LocalDate.now())
        );
    }
}
