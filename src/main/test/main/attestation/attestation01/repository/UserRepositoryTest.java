package main.attestation.attestation01.repository;

import main.attestation.attestation01.model.User;
import main.attestation.attestation01.repository.impl.UserRepositoryFileImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRepositoryTest {
    private UserRepositoryFileImpl userRepository;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() throws IOException {
        Path tempFile = Files.createFile(tempDir.resolve("test_users.txt"));
        userRepository = new UserRepositoryFileImpl(tempFile.toString());
    }

    @Test
    void createUser_success() {
        User user = new User("f5a8a3cb-4ac9|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true");
        Assertions.assertDoesNotThrow(() -> userRepository.create(user));
    }


    @Test
    void findById_success() {
        User foundUser = userRepository.findById("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2");
        Assertions.assertEquals("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2", foundUser.getId());
    }

    @Test
    void createUser_duplicateId_throwsException() {
        User user1 = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2|2024-12-25T19:10:11.556|noisescw32|123asd|123asd|Жижков|Олег|Павлович|24|false");
        User user2 = new User("f5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true");
        userRepository.create(user1);
        Exception exception = assertThrows(RuntimeException.class, () -> userRepository.create(user2));
        Assertions.assertTrue(exception.getMessage().contains("Ошибка при создании пользователя"));
    }

    @Test
    void findById_notFound_throwsException() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> userRepository.findById("999"));
        Assertions.assertEquals("Пользователь не найден.", exception.getMessage());
    }
}