package org.binar.cinema.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.binar.cinema.dto.ResponseData;
import org.binar.cinema.dto.SeatRequest;
import org.binar.cinema.dto.SeatResponse;
import org.binar.cinema.entities.Seat;
import org.binar.cinema.entities.SeatId;
import org.binar.cinema.services.impl.SeatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/seats")
public class SeatController {
    @Autowired
    SeatServiceImpl seatServiceImpl;

    @Operation(summary = "Create a new seat")
    @PostMapping("/create")
    public ResponseEntity<ResponseData> create(@RequestBody SeatRequest seat){
        log.info("Processing to create seat data");
        try{
            ResponseData data = new ResponseData();
            Seat input = seatServiceImpl.create(seat);
            SeatId id = input.getSeatId();
            SeatResponse response = new SeatResponse();
            response.setSeatNumber(id.getSeatNumber());
            response.setSeatRow(id.getSeatRow());
            response.setStudioId(input.getStudioId());
            response.setSeatStatus(input.isSeatStatus());
            data.setStatus("200");
            data.setMessagge("Seat successfully created");
            data.setData(response);
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Get all seats")
    @GetMapping("/get-all")
    public Iterable<Seat> findAll(){
        log.info("Processing to retrieve seats data");
        return seatServiceImpl.findAll();
    }

    @Operation(summary = "Get a seat by its id")
    @GetMapping("/get-one/{row}/{number}")
    public Seat findOne(@PathVariable Character row, @PathVariable Integer number){
        log.info("Processing to retrieve seat data by its id");
        return seatServiceImpl.findOne(new SeatId(row, number));
    }

    @Operation(summary = "Get seats by StudioId")
    @GetMapping("/get-seat-bystudio/{id}")
    public ResponseEntity<ResponseData> findByStudioId(@PathVariable Integer id){
        log.info("Processing to retrieve seats data by studio id");
        try{
            ResponseData data = new ResponseData();
            List<SeatResponse> list = new ArrayList<>();
            List<Seat> input = seatServiceImpl.findByStudioId(id);
            input.stream().map(x -> {
                SeatResponse response = new SeatResponse();
                SeatId sid = x.getSeatId();
                response.setSeatNumber(sid.getSeatNumber());
                response.setSeatRow(sid.getSeatRow());
                response.setStudioId(x.getStudioId());
                response.setSeatStatus(x.isSeatStatus());
                return response;
            }).forEach(list::add);
            data.setStatus("200");
            data.setMessagge("Seat successfully retrieved");
            data.setData(list);
            return ResponseEntity.ok(data);
        }catch(Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Update a seat by its id")
    @PutMapping("/update/{row}/{number}")
    public ResponseEntity<ResponseData> update(@PathVariable Character row, @PathVariable Integer number, @RequestBody SeatRequest seat){
        log.info("Processing to update seat data");
        try{
            ResponseData data = new ResponseData();
            seatServiceImpl.update(new SeatId(row, number), seat);
            Seat input = seatServiceImpl.findOne(new SeatId(row, number));
            SeatId id = input.getSeatId();
            SeatResponse response = new SeatResponse();
            response.setSeatNumber(id.getSeatNumber());
            response.setSeatRow(id.getSeatRow());
            response.setStudioId(input.getStudioId());
            response.setSeatStatus(input.isSeatStatus());
            data.setStatus("200");
            data.setMessagge("Seat successfully updated");
            data.setData(response);
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Delete a seat by its id")
    @DeleteMapping("/drop/{row}/{number}")
    public void delete(@PathVariable Character row, @PathVariable Integer number){
        log.info("Processing to delete seat data");
        seatServiceImpl.delete(new SeatId(row, number));
    }
}

