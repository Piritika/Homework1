package main.attestation.attestation01;

import main.attestation.attestation01.model.User;
import main.attestation.attestation01.repository.UserRepository;
import main.attestation.attestation01.repository.impl.UserRepositoryFileImpl;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        UserRepository userRepository = new UserRepositoryFileImpl();

        User user = new User("123|2023-12-25T19:10:11.556|noisemc_99|789ghs|789ghs|Жижков|Виктор|Павлович|25|true");
        System.out.println(user);

        userRepository.create(user);

        System.out.println(userRepository.findById("123"));

        List<User> users = userRepository.findAll();
        System.out.println(users);

        userRepository.update(userRepository.findById("123"));

         userRepository.deleteById("123");

        userRepository.deleteAll();

    }
}
