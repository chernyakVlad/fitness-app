package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.motivation.Motivation;
import com.training.fitnessappserver.entity.motivation.news.News;
import com.training.fitnessappserver.service.MotivationItemService;
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
    MotivationItemService motivationItemService;

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

    @PutMapping(value = "/{motivationId}/news")
    public ResponseEntity<Motivation> addNews(@PathVariable String motivationId, @RequestBody News news) {
        return new ResponseEntity<Motivation>(motivationService.addMotivationNews(motivationId, news), HttpStatus.OK);
    }
}
