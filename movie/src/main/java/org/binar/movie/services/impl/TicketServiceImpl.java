package org.binar.movie.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.binar.movie.dto.TicketModel;
import org.binar.movie.entities.Ticket;
import org.binar.movie.repositories.TicketRepo;
import org.binar.movie.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    TicketRepo ticketRepo;

    public Ticket create(TicketModel ticket){
        log.info("Creating ticket data");
        try{
            String created = LocalDateTime.now().toString();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            Ticket data = new Ticket(null, ticket.getUsername(), ticket.getScheduleId(), null, ticket.getSeatRow(), ticket.getSeatNumber(), LocalDateTime.parse(created, format), null);
            log.info("Ticket data has been created");
            return ticketRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when creating ticket data {}", e.getMessage());
            return null;
        }
    }
    public Ticket update(Integer id, TicketModel ticket){
        log.info("Updating ticket data");
        try{
            Ticket data = findOne(id);
            String updated = LocalDateTime.now().toString();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            data.setUsername(ticket.getUsername());
            data.setScheduleId(ticket.getScheduleId());
            data.setSeatRow(ticket.getSeatRow());
            data.setSeatNumber(ticket.getSeatNumber());
            data.setUpdateAt(LocalDateTime.parse(updated, format));
            log.info("Ticket data has been updated");
            return ticketRepo.save(data);
        }catch(Exception e){
            log.error("Error detected when updating ticket data {}", e.getMessage());
            return null;
        }
    }
    public Ticket findOne(Integer id){
        log.info("Retrieving the ticket data");
        Optional<Ticket> ticket = ticketRepo.findById(id);
        if (!ticket.isPresent()){
            log.info("Nothin was found");
            return null;
        }
        log.info("Ticket data has been retrieved");
        return ticket.get();
    }
    public Iterable<Ticket> findAll(){
        log.info("Retrieving the tickets data");
        return ticketRepo.findAll();
    }
    public void delete(Integer id){
        log.info("Deleting ticket data");
        ticketRepo.deleteById(id);
    }
}
