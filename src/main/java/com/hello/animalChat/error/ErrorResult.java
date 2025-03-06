package com.hello.animalChat.error;

public class ErrorResult {
    private int status;
    private String message;

    public ErrorResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
