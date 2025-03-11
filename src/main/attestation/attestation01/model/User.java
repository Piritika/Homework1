package main.attestation.attestation01.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class User {

    private String id;
    private LocalDateTime date;
    private String login;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String secondName;
    private String middleName;
    private int age;
    private boolean isWorker;

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }

    @Override
    public String toString() {
        return id + "|" + date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "|" + login + "|" +
                password + "|" + confirmPassword + "|" + firstName + "|" + secondName + "|" + middleName + "|" +
                age + "|" + isWorker;
    }

    public User(String line) {
        String[] elements = line.split("\\|");

        if (elements.length < 10) {
            throw new ArrayIndexOutOfBoundsException("Некорректный ввод данных.");
        }

        this.id = elements[0];

        if (elements[1].isBlank()) {
            this.date = LocalDateTime.now();
        } else {
            this.date = LocalDateTime.parse(elements[1]);
        }

        try {
            if (elements[2].length() >= 20) {
                throw new IllegalArgumentException("Логин не может содержать 20 и более символов.");
            } else {
                if (!elements[2].matches(".*[a-zA-Z].*")) {
                    throw new IllegalArgumentException("Логин не может содержать только цифры.");
                }
            }
        } finally {
            this.login = elements[2];
        }

        if (elements[3].length() >= 20) {
            throw new IllegalArgumentException("Пароль не может содержать 20 и более символов.");
        } else {
            if (elements[3].matches(".*\\\\d.*")) {
                throw new IllegalArgumentException("Пароль не должен содержать только цифры.");
            } else {
                if (elements[3].isBlank() && elements[4].isBlank()) {
                    throw new IllegalArgumentException("Поля паролей не могут быть пустыми. Заполните поля.");
                } else {
                    if (!elements[3].equals(elements[4])) {
                        throw new IllegalArgumentException("Поля паролей должны быть одинаковы.");
                    } else {
                        this.password = elements[3];
                        this.confirmPassword = elements[4];
                    }
                }
            }
        }

        if (elements[5].isBlank()) {
            throw new IllegalArgumentException("Поля имени и фамилии не могут быть пустыми. Заполните поля.");
        } else {
            for (char l : elements[5].toCharArray())
                if (Character.isDigit(l)) {
                    throw new IllegalArgumentException("Эти поля не могут содержать цифры.");
                } else {
                    this.firstName = elements[5];
                }
        }
        if (elements[6].isBlank()) {
            throw new IllegalArgumentException("Поля имени и фамилии не могут быть пустыми. Заполните поля.");
        } else {
            for (char l : elements[6].toCharArray())
                if (Character.isDigit(l)) {
                    throw new IllegalArgumentException("Эти поля не могут содержать цифры.");
                } else {
                    this.secondName = elements[6];
                }
        }
        for (char l : elements[7].toCharArray()) {
            if (Character.isDigit(l)) {
                throw new IllegalArgumentException("Поле отчества не может содержать цифры.");
            } else {
                this.middleName = elements[7];
            }
            this.age = Integer.parseInt(elements[8]);

            this.isWorker = Boolean.parseBoolean(elements[9]);

        }
    }

    public String getId() {
        return id;
    }
}
