package Exceptions;

public class UnsellableBookException extends RuntimeException {
    public UnsellableBookException(String message) {
        super(message);
    }
}
