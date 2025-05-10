package main.attestation.attestation01.exceptions;

public class IllegalArgumentException extends RuntimeException {
    private static String message;

    public IllegalArgumentException(String message) {
        this.message = message;
    }
}
