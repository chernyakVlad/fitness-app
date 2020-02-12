package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.plan.Activity;
import com.training.fitnessappserver.entity.plan.Plan;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.PlanRepository;
import com.training.fitnessappserver.service.ActivityService;
import com.training.fitnessappserver.service.PlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

@Service
public class PlanServiceImpl implements PlanService {
    PlanRepository planRepository;
    ActivityService activityService;

    @Autowired
    public PlanServiceImpl(PlanRepository planRepository, ActivityService activityService) {
        this.planRepository = planRepository;
        this.activityService = activityService;
    }


    @Override
    public Activity addPlanActivity(String planId, Activity activity) {
        Plan plan = getById(planId);
        Activity activity1 = activityService.addActivity(activity);
        plan.getActivities().add(activity1);
        planRepository.save(plan);
        return activity1;


    }

    @Override
    public Plan getByUserId(String userId) {
        LocalDate date = LocalDate.now();
        return getPlanByUserIdAndDate(userId, date);
    }


    @Override
    public Plan getById(String planId) {

        return planRepository.findById(planId).orElseThrow(() -> new ItemNotFoundException("There is no plan with id" + planId));
    }


    @Override
    public Plan addPlan(Plan plan) {
        if (plan != null) {
            return planRepository.insert(plan);
        } else {
            throw new ItemNotFoundException("There is no plan to add");
        }
    }

    @Override
    public Plan update(String userId, Plan plan) {
        Plan updatePlan = this.getPlanByUserIdAndDate(userId, plan.getDate());
        BeanUtils.copyProperties(plan, updatePlan, "planId");
        return planRepository.save(updatePlan);

    }

    @Override
    public Activity updateActivity(String activityId, Activity activity) {
        return activityService.update(activityId, activity);

    }

    @Override
    public void deleteActivity(String planId, String activityId) {
        Plan plan = getById(planId);
        plan.getActivities().remove(activityService.getById(activityId));
        activityService.delete(activityId);
    }

    @Override
    public Plan getPlanByUserIdAndDate(String userId, LocalDate date) {
        Optional<Plan> plan = planRepository.getPlanByUserIdAndDate(userId, date);
        return plan.orElseGet(() ->
                createInitialPlan(userId, date));
    }

    private Plan createInitialPlan(String userId, LocalDate date) {
        Plan plan1 = new Plan(userId, date);
        plan1.setActivities(initialActivities(userId, date));
        return addPlan(plan1);
    }

    @Override
    public Set<Activity> getActivitiesForDay(String planId) {
        return getById(planId).getActivities();
    }


    public SortedSet<Activity> initialActivities(String userId, LocalDate date) {
        SortedSet<Activity> activities = new TreeSet<>();
        activities.add(activityService.addActivity(new Activity("Breakfast", " ", false, LocalTime.of(9, 45), LocalTime.of(10, 15))));
        activities.add(activityService.addActivity(new Activity("Morning activity", "Run or training for an hour", false, LocalTime.of(8, 0), LocalTime.of(9, 30))));
        activities.add(activityService.addActivity(new Activity("Work", " ", false, LocalTime.of(11, 0), LocalTime.of(13, 0))));
        activities.add(activityService.addActivity(new Activity("Lunch", " ", false, LocalTime.of(13, 15), LocalTime.of(13, 45))));
        activities.add(activityService.addActivity(new Activity("Work", " ", false, LocalTime.of(14, 0), LocalTime.of(16, 0))));
        activities.add(activityService.addActivity(new Activity("Second lunch", " ", false, LocalTime.of(16, 15), LocalTime.of(16, 45))));
        activities.add(activityService.addActivity(new Activity("Work", " ", false, LocalTime.of(17, 0), LocalTime.of(19, 0))));
        activities.add(activityService.addActivity(new Activity("Dinner", " ", false, LocalTime.of(20, 15), LocalTime.of(20, 45))));
        return activities;
    }

}
