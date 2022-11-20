package org.binar.cinema.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.binar.cinema.dto.ResponseData;
import org.binar.cinema.dto.StudioRequest;
import org.binar.cinema.entities.Studio;
import org.binar.cinema.services.impl.StudioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/studios")
public class StudioController {
    @Autowired
    StudioServiceImpl studioServiceImpl;

    @Operation(summary = "Create a studio")
    @PostMapping("/create")
    public ResponseEntity<ResponseData> create(@RequestBody StudioRequest studio){
        log.info("Processing to create studio data");
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("Studio successfully added");
            data.setData(studioServiceImpl.create(studio));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Update a studio by its id")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData> update(@PathVariable Integer id, @RequestBody StudioRequest studio){
        log.info("Processing to update studio data");
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("Studio successfully added");
            studioServiceImpl.update(id, studio);
            data.setData(studioServiceImpl.findOne(id));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Get all studios")
    @GetMapping("/get-all")
    public Iterable<Studio> findAll(){
        log.info("Processing to retrieve studios data");
        return studioServiceImpl.findAll();
    }

    @Operation(summary = "Get a studio by its id")
    @GetMapping("/get-one/{id}")
    public Studio findOne(@PathVariable Integer id){
        log.info("Processing to retrieve studio data");
        return studioServiceImpl.findOne(id);
    }

    @Operation(summary = "Delete a studio by its id")
    @DeleteMapping("/drop/{id}")
    public void delete(@PathVariable Integer id){
        log.info("Processing to delete studio data");
        studioServiceImpl.delete(id);
    }
}
