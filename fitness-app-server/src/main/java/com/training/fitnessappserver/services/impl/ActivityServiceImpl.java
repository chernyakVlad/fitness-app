package com.training.fitnessappserver.services.impl;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.services.ActivityService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Override
    public List<Activity> getDailyActivities(String userId, LocalDate date) {
        return Arrays.asList(
                new Activity("Утрення активность", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Завтра", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Работа", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Обед", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Работа", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Полдник", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Работа", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Ужин", "Пробежка 10 минут", false, LocalDate.now()),
                new Activity("Сон", "Пробежка 10 минут", false, LocalDate.now())
        );
    }
}
