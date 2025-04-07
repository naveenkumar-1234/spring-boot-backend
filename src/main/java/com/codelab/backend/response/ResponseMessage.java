package com.codelab.backend.response;

public class ResponseMessage {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseMessage() {
    }

    public ResponseMessage(String message) {
        this.message = message;
    }
}
