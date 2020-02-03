package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.plan.Activity;
import com.training.fitnessappserver.entity.plan.Plan;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.ActivityRepository;
import com.training.fitnessappserver.repository.PlanRepository;
import com.training.fitnessappserver.service.PlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {
    PlanRepository planRepository;
    PlanService planService;

    @Autowired
    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public Plan addPlanActivity(String planId, Activity activity) {
        Plan plan = getById(planId);
        plan.getActivities().add(activity);
        return planRepository.save(plan);


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
    public Plan save(Plan plan) {
        return planRepository.insert(plan);
    }

    @Override
    public Plan update(String userId, Plan plan) {
        Plan updatePlan = this.getPlanByUserIdAndDate(userId, plan.getDate());
        BeanUtils.copyProperties(plan, updatePlan, "planId");
        return save(updatePlan);

    }


    @Override
    public Plan getPlanByUserIdAndDate(String userId, LocalDate date) {
        Optional<Plan> plan = planRepository.getPlanByUserIdAndDate(userId, date);
        return plan.orElseGet(() -> save(new Plan(userId, date)));
    }

    @Override
    public List<Activity> getActivitiesForDay(String planId) {
        List<Activity> activities = planService.getById(planId).getActivities();
        activities.sort(Comparator.comparing(Activity::getStart));
        return activities;
    }

}
