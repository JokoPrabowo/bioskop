import org.binar.cinema.dto.StudioRequest;
import org.binar.cinema.entities.Studio;
import org.binar.cinema.repositories.StudioRepo;
import org.binar.cinema.services.impl.StudioServiceImpl;
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
public class StudioTest {

    @Autowired
    private StudioServiceImpl studioServiceImpl;

    @MockBean
    private StudioRepo studioRepo;

    @BeforeEach
    void setup(){
        Optional<Studio> studio = Optional.of(new Studio(null, "Alpha"));
        Mockito.when(studioRepo.findById(1)).thenReturn(studio);
    }

    @Test
    @DisplayName("Post Method - Create studio")
    public void createStudio(){
        Studio data = new Studio(null, "Alpha");
        Mockito.when(studioRepo.save(data)).thenReturn(data);
        assertEquals(data, studioRepo.save(data));
    }

    @Test
    @DisplayName("Put Method - Update studio")
    public void updateStudio(){
        Studio data = new Studio(null, "Omega");
        StudioRequest model = new StudioRequest("Omega");
        Mockito.when(studioServiceImpl.update(1, model)).thenReturn(data);
        assertEquals(data, studioServiceImpl.update(1, model));
    }

    @Test
    @DisplayName("Get Method - Get studio by id")
    public void getStudioById(){
        String name = "Alpha";
        Studio studio = studioServiceImpl.findOne(1);
        assertEquals(name, studio.getStudioName());
    }

    @Test
    @DisplayName("Get Method - Get all studios")
    public void getAllStudios(){
        List<Studio> studios = new ArrayList<>();
        assertEquals(studios, studioServiceImpl.findAll());
    }
}
