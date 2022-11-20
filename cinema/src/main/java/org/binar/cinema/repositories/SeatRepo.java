package org.binar.cinema.repositories;

import org.binar.cinema.entities.Seat;
import org.binar.cinema.entities.SeatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepo extends JpaRepository<Seat, SeatId> {
    List<Seat> findByStudioId(Integer id);
}
