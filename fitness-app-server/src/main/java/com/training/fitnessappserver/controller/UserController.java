package com.training.fitnessappserver.controller;

import com.training.fitnessappserver.entity.user.User;
import com.training.fitnessappserver.entity.user.UserParameters;
import com.training.fitnessappserver.service.UserService;
import com.training.fitnessappserver.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<List<User>>(userService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getById(@PathVariable String id) {
        return new ResponseEntity<User>(userService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "get user parameters from date to date", notes = "")
    @GetMapping(value = "/{id}/parameters")
    public ResponseEntity<?> getUserParametersHistory(@PathVariable String id,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        return new ResponseEntity<>(userService.getUserParametersHistory(id, from, to), HttpStatus.OK);
    }

    @GetMapping(value = "/login/{login}")
    public ResponseEntity<User> getByLogin(@PathVariable String login) {
        return new ResponseEntity<User>(userService.findByLogin(login), HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<User> save(@RequestBody User user) {
        return new ResponseEntity<User>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User user) {
        return new ResponseEntity<User>(userService.update(id, user), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/parameters")
    public ResponseEntity<User> updateUserParameters(@PathVariable String id, @RequestBody UserParameters userParameters) {
        return new ResponseEntity<User>(userService.updateUserParameters(id, userParameters), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/goal")
    public ResponseEntity<User> setUserGoal(@PathVariable String id) {
        User user = userService.findById(id);
        user.setHasGoal(true);
        return new ResponseEntity<User>(userService.update(id, user), HttpStatus.OK);
    }
}
