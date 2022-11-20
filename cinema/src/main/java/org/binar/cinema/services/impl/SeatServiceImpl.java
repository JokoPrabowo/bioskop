package org.binar.cinema.services.impl;

import org.binar.cinema.dto.SeatRequest;
import org.binar.cinema.entities.Seat;
import org.binar.cinema.entities.SeatId;
import org.binar.cinema.repositories.SeatRepo;
import org.binar.cinema.services.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SeatServiceImpl implements SeatService {
    @Autowired
    SeatRepo seatRepo;

    public Seat create(SeatRequest seat){
        log.info("Creating new seat");
        try{
            Seat data = new Seat(new SeatId(seat.getSeatRow(), seat.getSeatNumber()), seat.getStudioId(), null, true);
            log.info("Seat has been created");
            return  seatRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when creating seat data {}", e.getMessage());
            return null;
        }
    }
    public Seat update(SeatId id, SeatRequest seat){
        log.info("Updating seat");
        try{
            Seat data = findOne(id);
            data.setSeatStatus(seat.isSeatStatus());
            log.info("Seat has been updated");
            return  seatRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when updating seat data {}", e.getMessage());
            return null;
        }
    }
    public Seat findOne(SeatId id){
        log.info("Retrieving the seat");
        try{
            Optional<Seat> seat = seatRepo.findById(id);
            if (!seat.isPresent()){
                log.info("Nothin was found");
                return null;
            }
            log.info("Seat has been retrieved");
            return seat.get();
        }catch(Exception e){
            log.error("Error detected when retrieving seat data {}", e.getMessage());
            return null;
        }
    }
    public List<Seat> findByStudioId(Integer id){
        log.info("Retrieveing the seats by studio id");
        return seatRepo.findByStudioId(id);
    }
    public Iterable<Seat> findAll(){
        log.info("Retrieveing the seats");
        return seatRepo.findAll();
    }
    public void delete(SeatId id){
        log.info("Deleting the seat");
        seatRepo.deleteById(id);
    }
}
