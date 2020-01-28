package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.motivation.Motivation;
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
        return new ResponseEntity<>(motivationService.save(motivation), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Motivation> updateMotivation(@PathVariable String id, @RequestBody Motivation motivation) {
        return new ResponseEntity<Motivation>(motivationService.update(id, motivation), HttpStatus.OK);
    }
}
