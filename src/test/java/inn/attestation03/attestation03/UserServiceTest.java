package inn.attestation03.attestation03;

import inn.attestation03.attestation03.model.dto.UserDTO;
import inn.attestation03.attestation03.model.entity.User;
import inn.attestation03.attestation03.repository.UserRepository;
import inn.attestation03.attestation03.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testCreateUser() {
        UserDTO dto = new UserDTO();
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmail("john@example.com");
        dto.setAge(25);
        userService.createUser(dto);

        List<User> users = userRepository.findAll();
        Assertions.assertEquals(1, users.size());
        Assertions.assertEquals("john@example.com", users.get(0).getEmail());
    }

    @Test
    void testUpdateUser() {
        User user = userRepository.save(User.builder()
                .firstName("Jane").lastName("Smith").email("jane@smith.com").age(25).build());

        UserDTO dto = new UserDTO();
        dto.setFirstName("Janet");
        dto.setLastName("Smith");
        dto.setEmail("jane@smith.com");
        dto.setAge(25);
        userService.updateUser(user.getId(), dto);

        User updated = userRepository.findById(user.getId()).orElseThrow();
        Assertions.assertEquals("Janet", updated.getFirstName());
    }

    @Test
    void testSoftDeleteUser() {
        User user = userRepository.save(User.builder()
                .firstName("Delete").lastName("Me").email("delete@me.com").age(99).build());

        userService.softDeleteUser(user.getId());
        Assertions.assertTrue(userRepository.findById(user.getId()).orElseThrow().isSoftDeleted());
    }

    @Test
    void testGetUsersExcludesSoftDeleted() {
        userRepository.save(User.builder().firstName("A").lastName("B").email("a@b.com").age(20).softDeleted(false).build());
        userRepository.save(User.builder().firstName("X").lastName("Y").email("x@y.com").age(30).softDeleted(true).build());

        List<UserDTO> result = userService.getUsers();
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("a@b.com", result.get(0).getEmail());
    }
}
