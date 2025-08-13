package org.skypro.counter.exception;

public class ShopError {
    private final String code;
    private final String massage;

    public String getCode() {
        return code;
    }

    public String getMassage() {
        return massage;
    }

    public ShopError(String code , String massage) {
        this.code = code;
        this.massage = massage;
    }
}
