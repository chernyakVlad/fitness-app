package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.Plan;
import com.training.fitnessappserver.service.PlanService;
import com.training.fitnessappserver.service.impl.PlanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(planService.save(plan), HttpStatus.CREATED);
    }
}
