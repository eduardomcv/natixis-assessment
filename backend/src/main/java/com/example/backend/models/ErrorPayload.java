package com.example.backend.models;

public class ErrorPayload {
    public String errorMessage;

    public ErrorPayload(String message) {
        this.errorMessage = message;
    }
}
