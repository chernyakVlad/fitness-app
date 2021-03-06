package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.exercise.Exercise;
import com.training.fitnessappserver.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/exercises")
@CrossOrigin(origins = "http://localhost:3000")
public class ExerciseController {

    ExerciseService exerciseService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping(value = "/today")
    public ResponseEntity<Exercise> getExerciseOnDate(@RequestParam String goalId) {
        return new ResponseEntity<Exercise>(exerciseService.getExerciseForToday(goalId), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Exercise> getExerciseOnDate(@PathVariable String id, @RequestBody Exercise exercise) {
        return new ResponseEntity<Exercise>(exerciseService.update(id, exercise), HttpStatus.OK);
    }
}
