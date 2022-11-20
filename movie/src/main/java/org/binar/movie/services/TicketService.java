package org.binar.movie.services;

import org.binar.movie.dto.TicketModel;
import org.binar.movie.entities.Ticket;

public interface TicketService {
    public Ticket create(TicketModel ticket);
    public Ticket update(Integer id,TicketModel ticket);
    public Ticket findOne(Integer id);
    public Iterable<Ticket> findAll();
    public void delete(Integer id);
}
