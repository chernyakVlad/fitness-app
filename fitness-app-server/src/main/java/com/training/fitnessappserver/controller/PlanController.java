package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.Goal;
import com.training.fitnessappserver.entity.Plan;
import com.training.fitnessappserver.service.PlanService;
import com.training.fitnessappserver.service.impl.PlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping(value = "/u/{userId}")
    public ResponseEntity<List<Plan>> getByUserId(@PathVariable String userId) {
        return new ResponseEntity<List<Plan>>(planService.getByUserId(userId), HttpStatus.OK);
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
    @PutMapping(value = "{id}/activity")
    public ResponseEntity<Plan> addPlanActivity(@PathVariable String id,
                                       @RequestBody Activity activity,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException();
        }
        activity.setPlanId(id);
        return new ResponseEntity<>(planService.addPlanActivity(id, activity), HttpStatus.CREATED);
    }

    @PostMapping(value = "")
    public ResponseEntity<Plan> save(@RequestBody Plan plan) {
        return new ResponseEntity<>(planService.save(plan), HttpStatus.CREATED);
    }
}
