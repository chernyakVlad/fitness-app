package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.Activity;
import com.training.fitnessappserver.entity.Exercise;
import com.training.fitnessappserver.entity.Goal;
import com.training.fitnessappserver.entity.enums.GoalType;
import com.training.fitnessappserver.services.exercise.ExerciseService;
import com.training.fitnessappserver.services.GoalService;
import com.training.fitnessappserver.services.activity.ActivityServiceFactory;
import com.training.fitnessappserver.services.exercise.ExerciseServiceFactory;
import com.training.fitnessappserver.services.exercise.ExerciseStoreService;
import com.training.fitnessappserver.services.exercise.impl.ExerciseForGainService;
import com.training.fitnessappserver.services.impl.GoalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/goals")
@CrossOrigin(origins = "http://localhost:3000")
public class GoalController {

    private GoalService goalService;
    private ExerciseServiceFactory exerciseServiceFactory;
    private ExerciseStoreService exerciseStoreService;
    private ActivityServiceFactory activityServiceFactory;

    @Autowired
    public GoalController(GoalServiceImpl goalService,
                          ActivityServiceFactory activityServiceFactory,
                          ExerciseServiceFactory exerciseServiceFactory,
                          ExerciseStoreService exerciseStoreService) {
        this.goalService = goalService;
        this.exerciseServiceFactory = exerciseServiceFactory;
        this.activityServiceFactory = activityServiceFactory;
        this.exerciseStoreService = exerciseStoreService;
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
    public ResponseEntity<Exercise> getExerciseForDay(@PathVariable String id) {
        Exercise exerciseForToday = exerciseServiceFactory.getExerciseService(GoalType.GAIN).getExerciseForToday(id);
        return new ResponseEntity<Exercise>(exerciseForToday , HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}/exercise/{exerciseId}")
    public ResponseEntity<Exercise> getExerciseForDay(@PathVariable String userId, @PathVariable String exerciseId, @RequestBody Exercise exercise) {
        return new ResponseEntity<Exercise>(exerciseStoreService.update(exerciseId, exercise), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/activities")
    public ResponseEntity<List<Activity>> getActivitiesForDay(@PathVariable String id, @RequestParam(required=false) LocalDate date) {
        if (date != null) {
            List<Activity> activities = activityServiceFactory.getActivityService(GoalType.GAIN).getDailyActivities(id, date);
            return new ResponseEntity<List<Activity>>(activities, HttpStatus.OK);
        }
        List<Activity> activities = activityServiceFactory.getActivityService(GoalType.GAIN).getDailyActivities(id, date);
        return new ResponseEntity<List<Activity>>(activities, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<Goal> save(@RequestBody Goal goal) {
        return new ResponseEntity<>(goalService.save(goal), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Goal> update(@PathVariable String id,
                                       @RequestBody Goal goal,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
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
