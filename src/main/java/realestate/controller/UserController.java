package com.example.realestate.controller;

import com.example.realestate.dto.UpdateUserRequest;
import com.example.realestate.dto.UserRequest;
import com.example.realestate.dto.response.UserResponse;
import com.example.realestate.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponse>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    public ResponseEntity<UserResponse> createAdvert(@RequestBody UserRequest request) {
        return new ResponseEntity<>(userService.saveUser(request), HttpStatus.CREATED);
    }

    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable(required = false) int advertNo) {
        return new ResponseEntity<>(userService.getUserById(advertNo), HttpStatus.OK);
    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable int id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUser(id, updateUserRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserByID(@PathVariable int id) {
        return ResponseEntity.ok(userService.deleteUserById(id));
    }
}

