package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.plan.Activity;
import com.training.fitnessappserver.entity.plan.Plan;
import com.training.fitnessappserver.service.PlanService;
import com.training.fitnessappserver.service.impl.PlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/plans")
@CrossOrigin(origins = "http://localhost:3000")
public class PlanController {
    PlanService planService;

    @Autowired
    public PlanController(PlanServiceImpl planService) {
        this.planService = planService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Plan> getById(@PathVariable String id) {
        return new ResponseEntity<Plan>(planService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/{planId}/activities")
    public ResponseEntity<Set<Activity>> getPlanActivities(@PathVariable String planId) {
        return new ResponseEntity<Set<Activity>>(planService.getActivitiesForDay(planId), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Plan> update(@PathVariable String id,
                                       @RequestBody Plan plan,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException();
        }
        return new ResponseEntity<>(planService.update(id, plan), HttpStatus.CREATED);
    }

    @PostMapping(value = "")
    public ResponseEntity<Plan> save(@RequestBody Plan plan) {
        return new ResponseEntity<>(planService.addPlan(plan), HttpStatus.CREATED);
    }

    @GetMapping(value = "/u/{userId}/{date}")
    public ResponseEntity<Plan> getByUserId(@PathVariable String userId, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return new ResponseEntity<Plan>(planService.getPlanByUserIdAndDate(userId, date), HttpStatus.OK);
    }

    @GetMapping(value = "/u/{userId}")
    public ResponseEntity<Plan> getByUserId(@PathVariable String userId) {
        return new ResponseEntity<Plan>(planService.getByUserId(userId), HttpStatus.OK);
    }

    @PostMapping(value = "/{planId}/activities")
    public ResponseEntity<Activity> addActivity(@PathVariable String planId, @RequestBody Activity activity) {
        return new ResponseEntity<Activity>(planService.addPlanActivity(planId, activity), HttpStatus.CREATED);
    }

    @PutMapping(value = "/activities/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable String id, @RequestBody Activity activity) {
        return new ResponseEntity<Activity>(planService.updateActivity(id, activity), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{planId}/activities/{activityId}")
    public ResponseEntity<?> delete(@PathVariable String planId, @PathVariable String activityId) {
        planService.deleteActivity(planId, activityId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
