package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.plan.Activity;
import com.training.fitnessappserver.service.ActivityService;
import com.training.fitnessappserver.service.impl.ActivityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/plans")
@CrossOrigin(origins = "http://localhost:3000")
public class ActivityController {
    ActivityService activityService;

    @Autowired
    public ActivityController(ActivityServiceImpl activityService) {
        this.activityService = activityService;
    }


    @PutMapping(value = "/activities/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable String id, @RequestBody Activity activity) {
        return new ResponseEntity<Activity>(activityService.update(id, activity), HttpStatus.OK);
    }

    @PostMapping(value = "/{planId}/activities")
    public ResponseEntity<Activity> addActivity(@PathVariable String planId, @RequestBody Activity activity) {
        return new ResponseEntity<Activity>(activityService.addActivity(planId, activity), HttpStatus.CREATED);
    }

}
