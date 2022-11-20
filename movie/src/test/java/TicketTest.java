import org.binar.movie.dto.TicketModel;
import org.binar.movie.entities.Ticket;
import org.binar.movie.repositories.TicketRepo;
import org.binar.movie.services.impl.TicketServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TicketTest {

    @Autowired
    private TicketServiceImpl ticketServiceImpl;

    @MockBean
    private TicketRepo ticketRepo;

    @BeforeEach
    void setup(){
        Optional<Ticket> ticket = Optional.of(new Ticket(null, "user", 1, null, 'A', 1));
        Mockito.when(ticketRepo.findById(1)).thenReturn(ticket);
    }

    @Test
    @DisplayName("Post Method - Create ticket")
    public void createTicket(){
        Ticket data = new Ticket(null, "user", 1, null, 'A', 1);
        Mockito.when(ticketRepo.save(data)).thenReturn(data);
        assertEquals(data, ticketRepo.save(data));
    }

    @Test
    @DisplayName("Put Method - Update ticket")
    public void updateTicket(){
        Ticket data = new Ticket(null, "user", 1, null, 'A', 2);
        TicketModel model = new TicketModel("user", 1,"Alpha", 'A', 2);
        Mockito.when(ticketServiceImpl.update(1, model)).thenReturn(data);
        assertEquals(data, ticketServiceImpl.update(1, model));
    }

    @Test
    @DisplayName("Get Method - Get ticket by id")
    public void getTicketById(){
        String user = "user";
        Ticket ticket = ticketServiceImpl.findOne(1);
        assertEquals(user, ticket.getUsername());
    }

    @Test
    @DisplayName("Get Method - Get all tickets")
    public void getAllTickets(){
        List<Ticket> tickets = new ArrayList<>();
        assertEquals(tickets, ticketServiceImpl.findAll());
    }
}

