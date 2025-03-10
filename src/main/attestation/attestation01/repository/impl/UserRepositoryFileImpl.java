package main.attestation.attestation01.repository.impl;

import main.attestation.attestation01.model.User;
import main.attestation.attestation01.repository.UserRepository;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class UserRepositoryFileImpl implements UserRepository {

    private final String filePath;

    public UserRepositoryFileImpl(String filePath) {
        this.filePath = filePath;
    }

    private static final List<User> USERS = new ArrayList<>();
    public static final String FILE_PATH = "src/main/resources/input.txt";


    @Override
    public void create(User user) throws RuntimeException {
        List<User> users = findAll();
        try {
            if (users.stream().anyMatch(existingUser -> existingUser.getId().equals(user.getId()))) {
                throw new RuntimeException("Пользователь с таким ID уже существует");
            }
        users.add(user);
        saveAll(users);
        appendUserToFile(user);
        System.out.println("Пользователь " + user.getFirstName() + " успешно создан.");
    } catch (Exception e) {
            throw new RuntimeException ("Ошибка создания пользователя");
        }

    }

    @Override
    public User findById(String id) {
        if (USERS.isEmpty()) {
            findAll();
        }
        return USERS.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Пользователь не найден."));
    }

    @Override
    public List<User> findAll() {
        if (USERS.isEmpty()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                List<User> usersFromFile = reader.lines()
                        .map(User::new)
                        .toList();

                USERS.addAll(usersFromFile);
                return USERS;
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла.");
                return Collections.emptyList();
            }
        }
        return USERS;
    }

    @Override
    public void update(User user) {
        try {
            List<User> users = findAll();
            Optional<User> existingUser = users.stream()
                    .filter(u -> u.getId().equals(user.getId()))
                    .findFirst();

            if (existingUser.isPresent()) {
                users.remove(existingUser.get());
                users.add(user);
                saveAll(users);
            } else {
                throw new NoSuchElementException("Пользователь с таким ID не найден");
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при обновлении пользователя", e);
        }

    }

    @Override
    public void deleteAll() throws FileNotFoundException {
        USERS.clear();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bufferedWriter.write("");
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new FileNotFoundException(e.getMessage());
        }
        System.out.println("Удаление пользователей прошло успешно.");
    }


    @Override
    public void deleteById(String id) {
        try {
            List<User> users = findAll().stream()
                    .filter(user -> !user.getId().equals(id))
                    .collect(Collectors.toList());
            if (users.size() == findAll().size()) {
                throw new NoSuchElementException("Пользователя с заданным идентификатором не существует");
            }
            saveAll(users);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при удалении пользователя по ID", e);
        }
    }

    private void saveAll(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                writer.write(formatUser(user) + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл", e);
        }
    }

    private void appendUserToFile(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(formatUser(user) + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл", e);
        }
    }

    private String formatUser(User user) {
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s|%d|%b",
                user.getId(), user.getDate(), user.getLogin(), user.getPassword(),
                user.getConfirmPassword(), user.getSecondName(), user.getFirstName(),
                user.getMiddleName() == null ? "" : user.getMiddleName(),
                user.getAge(), user.isWorker());
    }

}
