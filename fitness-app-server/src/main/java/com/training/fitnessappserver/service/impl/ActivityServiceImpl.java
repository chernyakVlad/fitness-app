package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.User;
import com.training.fitnessappserver.repository.ActivityRepository;
import com.training.fitnessappserver.repository.UserRepository;
import com.training.fitnessappserver.service.ActivityService;
import com.training.fitnessappserver.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    UserService userService;
    ActivityRepository activityRepository;
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

    @Override
    public Activity getActivityByDateAndUserId(String userId, LocalDate date) {
        User user = userService.findById(userId);

//TODO
        return null;
    }
}
