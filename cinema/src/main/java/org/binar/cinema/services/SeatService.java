package org.binar.cinema.services;

import org.binar.cinema.dto.SeatRequest;
import org.binar.cinema.entities.Seat;
import org.binar.cinema.entities.SeatId;

import java.util.List;

public interface SeatService {
    public Seat create(SeatRequest seat);
    public Seat update(SeatId id, SeatRequest seat);
    public Seat findOne(SeatId id);
    public List<Seat> findByStudioId(Integer id);
    public Iterable<Seat> findAll();
    public void delete(SeatId id);
}
