package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.Goal;
import com.training.fitnessappserver.entity.GoalConfig;
import com.training.fitnessappserver.entity.enums.GoalType;
import com.training.fitnessappserver.service.GoalConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/goal-configs")
@CrossOrigin(origins = "http://localhost:3000")
public class GoalConfigController {

    GoalConfigService  goalConfigService;

    @Autowired
    public GoalConfigController(GoalConfigService goalConfigService) {
        this.goalConfigService = goalConfigService;
    }

    @GetMapping(value = "/{type}")
    public ResponseEntity<GoalConfig> getByGoalType(@PathVariable GoalType type) {
        return new ResponseEntity<GoalConfig>(goalConfigService.get(type), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<GoalConfig> save(@RequestBody GoalConfig config) {
        return new ResponseEntity<>(goalConfigService.save(config), HttpStatus.CREATED);
    }

    @PutMapping(value = "")
    public ResponseEntity<GoalConfig> update(@RequestBody GoalConfig config) {
        return new ResponseEntity<>(goalConfigService.update(config), HttpStatus.CREATED);
    }
}
