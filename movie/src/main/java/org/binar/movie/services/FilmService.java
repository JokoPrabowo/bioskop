package org.binar.movie.services;

import org.binar.movie.dto.FilmModel;
import org.binar.movie.entities.Film;

import java.util.List;

public interface FilmService {
    public Film create(FilmModel film);
    public Film update(String filmCode, FilmModel film);
    public Film findOne(String code);
    public List<Film> isShowing();
    public Iterable<Film> findAll();
    public void delete(String code);
}
