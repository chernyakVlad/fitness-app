package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.motivation.Motivation;
import com.training.fitnessappserver.entity.motivation.MotivationItem;
import com.training.fitnessappserver.service.MotivationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/motivations")
@CrossOrigin(origins = "http://localhost:3000")
public class MotivationController {
    MotivationService motivationService;

    @Autowired
    public MotivationController(MotivationService motivationService) {
        this.motivationService = motivationService;
    }

    @PostMapping(value = "")
    public ResponseEntity<Motivation> save(@RequestBody Motivation motivation) {
        return new ResponseEntity<>(motivationService.addMotivation(motivation), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{motivationId}")
    public ResponseEntity<Motivation> updateMotivation(@PathVariable String motivationId, @RequestBody Motivation motivation) {
        return new ResponseEntity<Motivation>(motivationService.update(motivationId, motivation), HttpStatus.OK);
    }

    @PostMapping(value = "/{motivationId}/motivationItems")
    public ResponseEntity<MotivationItem> addMotivationItem(@PathVariable String motivationId, @RequestBody MotivationItem motivationItem) {
        return new ResponseEntity<MotivationItem>(motivationService.addMotivationItem(motivationId, motivationItem), HttpStatus.OK);
    }

    @GetMapping(value = "/u/{userId}")
    public ResponseEntity<Motivation> getByUserId(@PathVariable String userId) {
        return new ResponseEntity<Motivation>(motivationService.getMotivationByUserId(userId), HttpStatus.OK);
    }
    @DeleteMapping(value = "/{motivationId}/motivationItems/{motivationItem}")
    public ResponseEntity<?> delete(@PathVariable String motivationId, @PathVariable String motivationItem) {
        motivationService.deleteMotivationItem(motivationId, motivationItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
