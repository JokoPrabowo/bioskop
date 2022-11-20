package org.binar.movie.controllers;

import org.binar.movie.dto.FilmModel;
import org.binar.movie.dto.ResponseData;
import org.binar.movie.entities.Film;
import org.binar.movie.services.impl.FilmServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/films")
public class FilmController {
    @Autowired
    FilmServiceImpl filmServiceImpl;

    @Operation(summary = "create new film")
    @PostMapping("/create")
    public ResponseEntity<ResponseData> create(@RequestBody FilmModel film){
        log.info("Processing to create film data");
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("Film successfully added");
            data.setData(filmServiceImpl.create(film));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Update a film")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData> update(@PathVariable String id, @RequestBody FilmModel film){
        log.info("Processing to update film data");
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("Film successfully updated");
            filmServiceImpl.update(id, film);
            data.setData(filmServiceImpl.findOne(id));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Get all films")
    @GetMapping("/get-all")
    public Iterable<Film> findAll(){
        log.info("Processing films data");
        return filmServiceImpl.findAll();
    }

    @Operation(summary = "Get a film by its id")
    @GetMapping("/get-one/{id}")
    public Film findOne(@PathVariable String id){
        log.info("Processing film data");
        return filmServiceImpl.findOne(id);
    }

    @Operation(summary = "Get showing films")
    @GetMapping("/get-showing")
    public List<Film> isShowing(){
        log.info("Processing films data");
        return filmServiceImpl.isShowing();
    }

    @Operation(summary = "Delete a film by its id")
    @DeleteMapping("/drop/{id}")
    public void delete(@PathVariable String id){
        log.info("Processing film data");
        filmServiceImpl.delete(id);
    }
}