package org.binar.user.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.binar.user.dto.UserRequest;
import org.binar.user.entities.User;
import org.binar.user.repositories.UserRepo;
import org.binar.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    public User create(UserRequest user){
        try{
            Optional<User> sample = userRepo.findById(user.getUsername());
            if (sample.isEmpty()){
                log.info("User has been created");
                String created = LocalDateTime.now().toString();
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                User data = new User(user.getUsername(), user.getEmail(), user.getPassword(),LocalDateTime.parse(created, format), null);
                return userRepo.save(data);
            }
            log.info("User with username {} already exist!", user.getUsername());
            return null;
        }catch (Exception e){
            log.error("Error has found when creating user! {}", e.getMessage());
            return null;
        }
    }

    public User update(String username, UserRequest user){
        try{
            Optional<User> sample = userRepo.findById(username);
            if (sample.isEmpty()){
                log.error("User not found!");
            }
            log.info("User with username {} has been updated!", username);
            String updated = LocalDateTime.now().toString();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            User data = findOne(username);
            data.setUsername(user.getUsername());
            data.setEmail(user.getEmail());
            data.setPassword(user.getPassword());
            data.setUpdateAt(LocalDateTime.parse(updated, format));
            return userRepo.save(data);
        }catch (Exception e){
            log.error("Error has found when updating user! {}", e.getMessage());
            return null;
        }
    }

    public User findOne(String username){
        try{
            log.info("User has been retrieved");
            return userRepo.findById(username).get();
        }catch (Exception e){
            log.error("User not found");
            return null;
        }
    }

    public Iterable<User> findAll(){
        log.info("Retrieving all users");
        return userRepo.findAll();
    }
}
