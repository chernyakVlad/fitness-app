package com.training.fitnessappserver.service.impl;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.Plan;
import com.training.fitnessappserver.exception.ItemNotFoundException;
import com.training.fitnessappserver.repository.ActivityRepository;
import com.training.fitnessappserver.repository.PlanRepository;
import com.training.fitnessappserver.service.ActivityService;
import com.training.fitnessappserver.service.PlanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlanServiceImpl implements PlanService {
    PlanRepository planRepository;
    ActivityRepository activityRepository;
@Autowired
    public PlanServiceImpl(PlanRepository planRepository, ActivityRepository activityRepository) {
        this.planRepository = planRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public Plan addPlanActivity(String id, Activity activity) {
        Optional<Plan> plan = planRepository.findById(id);
        new ActivityServiceImpl(activityRepository).save(activity);
        if (plan.isPresent()) {

            plan.get().getActivities().add(activity);
            return save(plan.get());
        }else{
            throw new ItemNotFoundException("There is no plan with id" + id);
        }

    }

    @Override
    public Plan getByUserId(String userId) {
        LocalDate date =LocalDate.now();
        Plan plans = getPlan(userId,date);

//        if (plan.isEmpty()) {
//            throw new ItemNotFoundException("There is no plan with id" + userId);
//        } else {
            return plans;
      //  }
    }


    @Override
    public Plan getById(String planId) {

        return planRepository.findById(planId).orElseThrow(() -> new ItemNotFoundException("There is no plan with id" + planId));
    }


    @Override
    public Plan save(Plan plan) {
        return planRepository.save(plan);
    }

    @Override
    public Plan update(String userId, Plan plan) {
        Plan updatePlan = this.getPlan(userId, plan.getDate());
        BeanUtils.copyProperties(plan, updatePlan, "planId");
        return save(updatePlan);

    }


    @Override
    public Plan getPlan(String userId, LocalDate date) {
        return planRepository.getPlanByUserIdAndDate(userId, date)
                .orElseThrow(() -> new ItemNotFoundException("There is no plan on date" + date.toString() + " user with userId " + userId));
    }

    @Override
    public List<Activity> getActivitiesForDay(String userId, LocalDate date) {

        return planRepository.getPlanActivitiesByUserIdAndDate(userId, date)
                .orElseThrow(() -> new ItemNotFoundException("There is no activity on date" + date + "and userId" + userId)).getActivities();
    }

}
