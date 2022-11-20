package org.binar.cinema.repositories;

import org.binar.cinema.entities.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioRepo extends JpaRepository<Studio, Integer> {
}
