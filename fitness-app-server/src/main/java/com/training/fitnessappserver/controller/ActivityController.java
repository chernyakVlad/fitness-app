package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.service.ActivityService;
import com.training.fitnessappserver.service.ExerciseService;
import com.training.fitnessappserver.service.impl.ActivityServiceImpl;
import com.training.fitnessappserver.service.impl.ExerciseStoreService;
import com.training.fitnessappserver.service.impl.GoalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/plans")
@CrossOrigin(origins = "http://localhost:3000")
public class ActivityController {
    private ActivityService activityService;
    @Autowired
    public ActivityController(ActivityServiceImpl activityService) {
        this.activityService = activityService;
    }

    @GetMapping(value = "/today")
    public ResponseEntity<List<Activity>> getActivitiesForDay(@PathVariable String id, @RequestParam(required = false) LocalDate date) {
        if (date != null) {
            List<Activity> activities = (List<Activity>) activityService.getActivitiesByDateAndUserId(id, date);
            return new ResponseEntity<List<Activity>>(activities, HttpStatus.OK);
        }
        List<Activity> activities = (List<Activity>) activityService.getActivitiesByDateAndUserId(id, date);
        return new ResponseEntity<List<Activity>>(activities, HttpStatus.OK);
    }
}
