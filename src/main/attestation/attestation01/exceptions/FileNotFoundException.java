package main.attestation.attestation01.exceptions;

public class FileNotFoundException extends RuntimeException {

        private final String message;

    public FileNotFoundException(String message) {

        this.message = "Ошибка чтения файла.";
    }
}
