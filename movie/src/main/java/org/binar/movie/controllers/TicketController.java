package org.binar.movie.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.binar.movie.dto.ResponseData;
import org.binar.movie.dto.TicketData;
import org.binar.movie.dto.TicketModel;
import org.binar.movie.entities.Film;
import org.binar.movie.entities.Schedule;
import org.binar.movie.entities.Ticket;
import org.binar.movie.services.impl.FilmServiceImpl;
import org.binar.movie.services.impl.InvoiceServiceImpl;
import org.binar.movie.services.impl.ScheduleServiceImpl;
import org.binar.movie.services.impl.TicketServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;

@Slf4j
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketServiceImpl ticketServiceImpl;

    @Autowired
    private FilmServiceImpl filmServiceImpl;

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;

    @Autowired
    private InvoiceServiceImpl invoiceServiceImpl;

    @Operation(summary = "Create a reservation")
    @PostMapping("/buy-ticket")
    public ResponseEntity<ResponseData> create(HttpServletResponse response, @RequestBody TicketModel ticket){
        log.info("Processing to create ticket data");
        try{
            ResponseData data = new ResponseData();
            TicketData tdata = new TicketData();
            Schedule schedules = scheduleServiceImpl.findOne(ticket.getScheduleId());
            Film film = filmServiceImpl.findOne(schedules.getFilmCode());
            tdata.setUsername(ticket.getUsername());
            tdata.setFilm(film.getFilmName());
            tdata.setPrice(schedules.getPrice());
            tdata.setStudioName(ticket.getStudioName());
            tdata.setSeatRow(ticket.getSeatRow());
            tdata.setSeatNumber(ticket.getSeatNumber());
            tdata.setShowDate(schedules.getShowDate());
            tdata.setStartAt(schedules.getStartAt());
            tdata.setEndAt(schedules.getEndAt());
            ticketServiceImpl.create(ticket);
            data.setStatus("200");
            data.setMessagge("Ticket successfully reserved");
            data.setData(tdata);
            ByteArrayInputStream invoice = new ByteArrayInputStream(invoiceServiceImpl.generateFile(tdata));
            response.addHeader("Content-Disposition", "attachment; filename=" + tdata.getUsername() + ".pdf");
            IOUtils.copy(invoice, response.getOutputStream());
            response.flushBuffer();
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Update a ticket")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData> update(@PathVariable Integer id, @RequestBody TicketModel ticket){
        log.info("Processing to update ticket data");
        try{
            ResponseData data = new ResponseData();
            data.setStatus("200");
            data.setMessagge("Ticket successfully updated");
            ticketServiceImpl.update(id, ticket);
            data.setData(ticketServiceImpl.findOne(id));
            return ResponseEntity.ok(data);
        }catch (Exception e){
            ResponseData data = new ResponseData();
            data.setStatus("400");
            data.setMessagge(e.getMessage());
            data.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(data);
        }
    }

    @Operation(summary = "Get all tickets")
    @GetMapping("/get-all")
    public Iterable<Ticket> findAll(){
        log.info("Processing to retrieve tickets data");
        return ticketServiceImpl.findAll();
    }

    @Operation(summary = "Get a ticket by its id")
    @GetMapping("/get-one/{id}")
    public Ticket findOne(@PathVariable Integer id){
        log.info("Processing to retrieve ticket data");
        return ticketServiceImpl.findOne(id);
    }

    @Operation(summary = "Delete a ticket by its id")
    @DeleteMapping("/drop/{id}")
    public void delete(@PathVariable Integer id){
        log.info("Processing to delete ticket data");
        ticketServiceImpl.delete(id);
    }
}
