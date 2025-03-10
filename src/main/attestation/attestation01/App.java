package main.attestation.attestation01;

import main.attestation.attestation01.model.User;
import main.attestation.attestation01.repository.UserRepository;
import main.attestation.attestation01.repository.impl.UserRepositoryFileImpl;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        UserRepository userRepository = new UserRepositoryFileImpl();

        User user = new User("8a65-c424e129b9d2|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Крылов|Виктор|Павлович|25|true");
        System.out.println(user);

        userRepository.create(user);

        System.out.println(userRepository.findById("5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2"));

        List<User> users = userRepository.findAll();
        System.out.println(users);

        userRepository.update(userRepository.findById("5a8a3cb-4ac9-4b3b-8a65-c424e129b9d2"));

        userRepository.deleteById("f5gzxvcb-4ac9-4b3b-8a65-c424e129b9d2");
        System.out.println("Удаление прошло успешно.");

        userRepository.deleteAll();

    }
}
