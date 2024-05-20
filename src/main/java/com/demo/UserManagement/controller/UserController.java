package com.demo.UserManagement.controller;

import com.demo.UserManagement.dto.ApiResponse;
import com.demo.UserManagement.dto.UserDto;
import com.demo.UserManagement.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        log.info("Creating user: {}", userDto);
        UserDto userDto1 = userService.createUser(userDto);
        log.info("User created successfully: {}", userDto1);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Long id) {
        log.info("Updating user with ID {}: {}", id, userDto);
        UserDto upUser = userService.updateUser(userDto, id);
        log.info("User updated successfully: {}", upUser);
        return new ResponseEntity<>(upUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        log.info("Deleting user with ID: {}", id);
        userService.deleteUser(id);
        log.info("User deleted with Id successfully: {}", id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successful", true), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        log.info("Fetching user with ID: {}", id);
        UserDto userDto = userService.getUser(id);
        log.info("User fetched successfully: {}", id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAll() {
        log.info("Fetching all user detaiils: ");
        List<UserDto> getAll = userService.getAll();
        log.info("Fetched {} users", getAll);
        return new ResponseEntity<>(getAll, HttpStatus.OK);
    }
}
