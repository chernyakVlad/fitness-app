package com.training.fitnessappserver.services.activity.impl;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.services.activity.ActivityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class ActivityForGainService implements ActivityService {
    @Override
    public List<Activity> getDailyActivities(String userId, LocalDate date) {
        return Arrays.asList(
                new Activity("GAIN_Утрення активность", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("GAIN_Завтрак", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("GAIN_Работа1", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("GAIN_Обед", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("GAIN_Работа2", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("GAIN_Полдник", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("GAIN_Работа3", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("GAIN_Ужин", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("GAIN_Сон", "Пробежка 10 минут", false, LocalDate.now())
        );
    }
}
