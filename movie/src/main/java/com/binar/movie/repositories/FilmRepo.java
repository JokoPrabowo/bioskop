package com.binar.movie.repositories;

import com.binar.movie.entities.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepo extends JpaRepository<Film, String> {
    @Query("SELECT f FROM Film f WHERE f.onShow >= CURRENT_DATE")
    List<Film> findOnShowingFilms();
}
