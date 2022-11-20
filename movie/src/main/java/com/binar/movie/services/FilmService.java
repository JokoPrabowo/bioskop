package com.binar.movie.services;

import com.binar.movie.dto.FilmModel;
import com.binar.movie.entities.Film;

import java.util.List;

public interface FilmService {
    public Film create(FilmModel film);
    public Film update(String filmCode, FilmModel film);
    public Film findOne(String code);
    public List<Film> isShowing();
    public Iterable<Film> findAll();
    public void delete(String code);
}
