package org.binar.movie.repositories;

import org.binar.movie.entities.Ticket;
import org.jfree.chart.axis.Tick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepo extends JpaRepository<Ticket, Integer> {
}
