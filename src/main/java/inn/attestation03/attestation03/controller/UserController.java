package inn.attestation03.attestation03.controller;

import inn.attestation03.attestation03.model.dto.UserDTO;
import inn.attestation03.attestation03.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> registerUser(@RequestBody @Valid UserDTO dto) {
        log.info("start registerUser");
        userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        log.info("start getAllUsers");
        return userService.getUsers();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable Long id, @RequestBody UserDTO dto) {
        log.info("start updateUser");
        userService.updateUser(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteUser(@PathVariable Long id) {
        log.info("start softDeleteUser");
        userService.softDeleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
