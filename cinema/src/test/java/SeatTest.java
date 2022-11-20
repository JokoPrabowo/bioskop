import org.binar.cinema.dto.SeatRequest;
import org.binar.cinema.entities.Seat;
import org.binar.cinema.entities.SeatId;
import org.binar.cinema.repositories.SeatRepo;
import org.binar.cinema.services.impl.SeatServiceImpl;
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
public class SeatTest {

    @Autowired
    private SeatServiceImpl seatServiceImpl;

    @MockBean
    private SeatRepo seatRepo;

    @BeforeEach
    void setup(){
        Optional<Seat> seat = Optional.of(new Seat(new SeatId('X', 1), 1, null, true));
        Mockito.when(seatRepo.findById(new SeatId('X', 1))).thenReturn(seat);
    }

    @Test
    @DisplayName("Post Method - Create seat")
    public void createSeat(){
        Seat data = new Seat(new SeatId('X', 1), 1, null, true);
        Mockito.when(seatRepo.save(data)).thenReturn(data);
        assertEquals(data, seatRepo.save(data));
    }

    @Test
    @DisplayName("Put Method - Update seat")
    public void updateSeat(){
        Seat data = new Seat(new SeatId('X', 1), 1, null, false);
        SeatRequest model = new SeatRequest('X', 1, 1, false);
        Mockito.when(seatServiceImpl.update(new SeatId('X', 1), model)).thenReturn(data);
        assertEquals(data, seatServiceImpl.update(new SeatId('X', 1), model));
    }

    @Test
    @DisplayName("Get Method - Get seat by id")
    public void getSeatById(){
        Boolean seatStatus = true;
        Seat seat = seatServiceImpl.findOne(new SeatId('X', 1));
        assertEquals(seatStatus, seat.isSeatStatus());
    }

    @Test
    @DisplayName("Get Method - Get all seats")
    public void getAllSeats(){
        List<Seat> seats = new ArrayList<>();
        assertEquals(seats, seatServiceImpl.findAll());
    }
}
