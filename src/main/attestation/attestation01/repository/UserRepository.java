package main.attestation.attestation01.repository;

import main.attestation.attestation01.model.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface UserRepository {

    void create(User user);

    User findById(String id);

    List<User> findAll();

    void update(User user) throws FileNotFoundException, IOException;

    void deleteById(String id) throws FileNotFoundException;

    void deleteAll() throws IOException;

}
