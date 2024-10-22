package com.task.manager.controller;

import com.task.manager.entity.UserEntity;
import com.task.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody UserEntity user) {
        UserEntity userSave = userService.saveUser(user);

        return new ResponseEntity<>("New user created " + userSave.getUsername(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserEntity user) {
        UserEntity updateUser = userService.updateUser(user);

        return new ResponseEntity<>(updateUser.getUsername() + " user successfully updated", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteUser(@RequestBody UserEntity user) {
        userService.deleteUser(user.getId());
    }

}
