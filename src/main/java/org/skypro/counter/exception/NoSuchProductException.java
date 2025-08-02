package org.skypro.counter.exception;

public class NoSuchProductException extends RuntimeException {
    public NoSuchProductException() {
        super("такого продукта не существует");
    }
}
