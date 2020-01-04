package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.Exercise;
import com.training.fitnessappserver.entity.Goal;
import com.training.fitnessappserver.services.ExerciseGenerationService;
import com.training.fitnessappserver.services.GoalService;
import com.training.fitnessappserver.services.impl.ExerciseGenerationServiceImpl;
import com.training.fitnessappserver.services.impl.GoalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/goals")
@CrossOrigin(origins = "http://localhost:3000")
public class GoalController {

    private GoalService goalService;
    private ExerciseGenerationService exerciseGenerationService;

    @Autowired
    public GoalController(GoalServiceImpl goalService,
                          ExerciseGenerationServiceImpl exerciseGenerationService) {
        this.goalService = goalService;
        this.exerciseGenerationService = exerciseGenerationService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Goal>> getAll() {
        return new ResponseEntity<List<Goal>>(goalService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Goal> getById(@PathVariable String id) {
        return new ResponseEntity<Goal>(goalService.getById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/u/{userId}")
    public ResponseEntity<List<Goal>> getByUserId(@PathVariable String userId) {
        return new ResponseEntity<List<Goal>>(goalService.getByUserId(userId), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/exercise")
    public ResponseEntity<Exercise> getExerciseForGoalForToday(@PathVariable String id) {
        return new ResponseEntity<Exercise>(exerciseGenerationService.getExerciseForToday(id), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/exercises")
    public ResponseEntity<List<Exercise>> getExerciseForGoalForMonth(@PathVariable String id) {
        return new ResponseEntity<List<Exercise>>(exerciseGenerationService.getExerciseForMonth(id), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Goal> save(@RequestBody Goal goal, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new RuntimeException();
        }
        return new ResponseEntity<>(goalService.save(goal), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Goal> update(@PathVariable String id,
                                       @RequestBody Goal goal,
                                       BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new RuntimeException();
        }
        return new ResponseEntity<>(goalService.update(id, goal), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        goalService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
