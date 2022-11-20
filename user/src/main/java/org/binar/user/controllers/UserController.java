package org.binar.user.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.binar.user.dto.ResponseData;
import org.binar.user.dto.UserRequest;
import org.binar.user.entities.User;
import org.binar.user.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @Operation(summary = "Register new account")
    @PostMapping("/signup")
    public ResponseEntity<ResponseData> signup(@RequestBody UserRequest user){
        log.info("Processing to create user data");
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("User successfully created");
            data.setData(userService.create(user));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Login to account")
    @GetMapping("/signin")
    public ResponseEntity<ResponseData> signin(@RequestBody UserRequest user){
        log.info("Processing login to account");
        try{
            ResponseData data = new ResponseData();
            User sample = userService.findOne(user.getUsername());
            if(user.getPassword().equals(user.getPassword())){
                data.setStatus("200");
                data.setMessagge("User successfully signin");
                data.setData(sample);
                return ResponseEntity.ok(data);
            }
            data.setStatus("400");
            data.setMessagge("Incorrect password");
            data.setData(null);
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Update a user by its username")
    @PutMapping("/update/{username}")
    public ResponseEntity<ResponseData> update(@PathVariable String username, @RequestBody UserRequest user){
        log.info("Processing to update user data");
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("User successfully updated");
            userService.update(username, user);
            data.setData(userService.findOne(username));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Get all users")
    @GetMapping("/get-all")
    public Iterable<User> findAll(){
        log.info("Processing to retrieve users data");
        return userService.findAll();
    }

    @Operation(summary = "Get a user by its username")
    @GetMapping("/get-one/{username}")
    public User findOne(@PathVariable String username){
        log.info("Processing to retrieve user data");
        return userService.findOne(username);
    }
}
