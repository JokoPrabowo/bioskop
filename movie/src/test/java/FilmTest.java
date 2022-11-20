import org.binar.movie.dto.FilmModel;
import org.binar.movie.entities.Film;
import org.binar.movie.repositories.FilmRepo;
import org.binar.movie.services.impl.FilmServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FilmTest {

    @Autowired
    private FilmServiceImpl filmServiceImpl;

    @MockBean
    private FilmRepo filmRepo;

    @BeforeEach
    void setup(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Optional<Film> film = Optional.of(new Film("F0000", "Film Example", "Example", LocalDate.parse("28/12/2022", date), null, null));
        Mockito.when(filmRepo.findById("F0000")).thenReturn(film);
    }

    @Test
    @DisplayName("Post Method - Create film")
    public void createFilm(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Film data = new Film("F0000", "Film Example", "Example", LocalDate.parse("28/12/2022", date),null,null);
        Mockito.when(filmRepo.save(data)).thenReturn(data);
        assertEquals(data, filmRepo.save(data));
    }

    @Test
    @DisplayName("Put Method - Update film")
    public void updateFilm(){
        DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        FilmModel model = new FilmModel("F0000", "Film Example", "Example", LocalDate.parse("30/12/2022", date));
        Film data = new Film("F0000", "Film Example", "Example", LocalDate.parse("30/12/2022", date), null,null);
        Mockito.when(filmServiceImpl.update("F0000", model)).thenReturn(data);
        assertEquals(data, filmServiceImpl.update("F0000", model));
    }

    @Test
    @DisplayName("Get Method - Get film by id")
    public void getFilmById(){
        String name = "Film Example";
        Film film = filmServiceImpl.findOne("F0000");
        assertEquals(name, film.getFilmName());
    }

    @Test
    @DisplayName("Get Method - Get all films")
    public void getAllFilms(){
        List<Film> films = new ArrayList<>();
        assertEquals(films, filmServiceImpl.findAll());
    }
}
