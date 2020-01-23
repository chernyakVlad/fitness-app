package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.GoalConfig;
import com.training.fitnessappserver.service.ActivityService;
import com.training.fitnessappserver.service.impl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/plans/activities")
@CrossOrigin(origins = "http://localhost:3000")
public class ActivityController {
    ActivityService activityService;

    @Autowired
    public ActivityController(ActivityServiceImpl activityService) {
        this.activityService = activityService;
    }

    @GetMapping(value = "/today")
    public ResponseEntity<List<Activity>> getActivitiesForDay(@PathVariable String id, @RequestParam(required = false) LocalDate date) {
        List<Activity> activities =  activityService.getActivitiesByDateAndUserId(id, date);
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable String id, @RequestBody Activity activity) {
        return new ResponseEntity<Activity>(activityService.update(id, activity), HttpStatus.OK);
    }
    @PostMapping(value = "")
    public ResponseEntity<Activity> save(@RequestBody Activity activity) {
        return new ResponseEntity<>(activityService.save(activity), HttpStatus.CREATED);
    }
}
