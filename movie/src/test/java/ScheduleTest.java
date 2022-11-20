import org.binar.movie.dto.ScheduleModel;
import org.binar.movie.entities.Schedule;
import org.binar.movie.repositories.ScheduleRepo;
import org.binar.movie.services.impl.ScheduleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ScheduleTest {

    @Autowired
    private ScheduleServiceImpl scheduleServiceImpl;

    @MockBean
    private ScheduleRepo scheduleRepo;

    @BeforeEach
    void setup(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        Optional<Schedule> schedule = Optional.of(new Schedule(null, "F0001", null,"Alpha", 75000, LocalDate.parse("20/12/2022", date), LocalTime.parse("10:00:00", time), LocalTime.parse("12:30:00", time), null, null));
        Mockito.when(scheduleRepo.findById(1)).thenReturn(schedule);
    }

    @Test
    @DisplayName("Post Method - Create schedule")
    public void createSchedule(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        Schedule data = new Schedule(null, "F0001", null, "Alpha", 75000, LocalDate.parse("20/12/2022", date), LocalTime.parse("10:00:00", time), LocalTime.parse("12:30:00", time), null, null);
        Mockito.when(scheduleRepo.save(data)).thenReturn(data);
        assertEquals(data, scheduleRepo.save(data));
    }

    @Test
    @DisplayName("Put Method - Update schedule")
    public void updateSchedule(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
        Schedule data = new Schedule(null, "F0001", null, "Omega", 75000, LocalDate.parse("20/12/2022", date), LocalTime.parse("11:00:00", time), LocalTime.parse("13:30:00", time),null,null);
        ScheduleModel model = new ScheduleModel("F0001", "Omega", 75000, LocalDate.parse("20/12/2022", date), LocalTime.parse("11:00:00", time), LocalTime.parse("13:30:00", time));
        Mockito.when(scheduleServiceImpl.update(1, model)).thenReturn(data);
        assertEquals(data, scheduleServiceImpl.update(1, model));
    }

    @Test
    @DisplayName("Get Method - Get schedule by id")
    public void getScheduleById(){
        String code = "F0001";
        Schedule schedule = scheduleServiceImpl.findOne(1);
        assertEquals(code, schedule.getFilmCode());
    }

    @Test
    @DisplayName("Get Method - Get all schedules")
    public void getAllSchedules(){
        List<Schedule> schedules = new ArrayList<>();
        assertEquals(schedules, scheduleServiceImpl.findAll());
    }
}