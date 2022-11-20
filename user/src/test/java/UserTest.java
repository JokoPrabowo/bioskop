import org.binar.user.dto.UserRequest;
import org.binar.user.entities.User;
import org.binar.user.repositories.UserRepo;
import org.binar.user.services.impl.UserServiceImpl;
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
public class UserTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @MockBean
    private UserRepo userRepo;

    @BeforeEach
    void setup(){
        Optional<User> user = Optional.of(new User("user", "user@gmail.com", "user123", null, null));
        Mockito.when(userRepo.findById("user")).thenReturn(user);
    }

    @Test
    @DisplayName("Post Method - Create user")
    public void createUSer(){
        User data = new User("user", "user@gmail.com", "user123", null, null);
        Mockito.when(userRepo.save(data)).thenReturn(data);
        assertEquals(data, userRepo.save(data));
    }

    @Test
    @DisplayName("Put Method - Update user")
    public void updateUser(){
        User data = new User("user", "user@gmail.com", "user234", null, null);
        UserRequest model = new UserRequest("user", "user@gmail.com", "user234");
        Mockito.when(userServiceImpl.update("user", model)).thenReturn(data);
        assertEquals(data, userServiceImpl.update("user", model));
    }

    @Test
    @DisplayName("Get Method - Get user by id")
    public void getUserById(){
        String email = "user@gmail.com";
        User user = userServiceImpl.findOne("user");
        assertEquals(email, user.getEmail());
    }

    @Test
    @DisplayName("Get Method - Get all users")
    public void getAllUsers(){
        List<User> users = new ArrayList<>();
        assertEquals(users, userServiceImpl.findAll());
    }
}
