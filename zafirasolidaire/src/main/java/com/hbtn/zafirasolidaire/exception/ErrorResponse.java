package com.hbtn.zafirasolidaire.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private String path;
    private int status;
    private LocalDateTime timestamp;

    public ErrorResponse(String message, String path, int status) {
        this.message = message;
        this.path = path;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ErrorResponse setPath(String path) {
        this.path = path;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public ErrorResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ErrorResponse setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
