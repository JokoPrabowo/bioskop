package org.binar.movie.services.impl;

import org.binar.movie.dto.FilmModel;
import org.binar.movie.entities.Film;
import org.binar.movie.repositories.FilmRepo;
import org.binar.movie.services.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    FilmRepo filmRepo;

    public Film create(FilmModel film){
        log.info("Creating new film");
        try{
            String created = LocalDateTime.now().toString();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            Film data = new Film(film.getFilmCode(), film.getFilmName(), film.getCategory(), film.getOnShow(), LocalDateTime.parse(created, format), null);
            log.info("Film has been created");
            return filmRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when creating film data {}", e.getMessage());
            return null;
        }
    }
    public Film update(String code, FilmModel film){
        log.info("Updating film data");
        try{
            Film data = findOne(code);
            String updated = LocalDateTime.now().toString();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            data.setFilmCode(film.getFilmCode());
            data.setFilmName(film.getFilmName());
            data.setCategory(film.getCategory());
            data.setOnShow(film.getOnShow());
            data.setUpdateAt(LocalDateTime.parse(updated, format));
            log.info("Film has been updated");
            return filmRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when updating film data {}", e.getMessage());
            return null;
        }
    }
    public Film findOne(String code){
        log.info("Retrieving the film");
        try{
            Optional<Film> film= filmRepo.findById(code);
            if (!film.isPresent()){
                log.info("Nothing was found");
                return null;
            }
            log.info("Film has been retrieved");
            return film.get();
        }catch(Exception e){
            log.error("Error detected when retrieving film data {}", e.getMessage());
            return null;
        }
    }
    public Iterable<Film> findAll(){
        log.info("Retrieving the films");
        return filmRepo.findAll();
    }

    public List<Film> isShowing(){
        log.info("Retrieving the not showed films");
        return filmRepo.findOnShowingFilms();
    }
    public void delete(String code){
        log.info("Deleting films");
        filmRepo.deleteById(code);
    }
}
