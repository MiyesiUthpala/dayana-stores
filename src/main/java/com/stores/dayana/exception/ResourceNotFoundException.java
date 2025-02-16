package com.stores.dayana.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// This annotation indicates that the response status should be NOT_FOUND (404) when this exception is thrown
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L; // Optional for serialization

    public ResourceNotFoundException(String message) {
        super(message); // Pass the message to the parent RuntimeException
    }
}
