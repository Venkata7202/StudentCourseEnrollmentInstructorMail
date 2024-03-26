package com.annamcharya.stucourseins.exceptions;

public class InstructorNotFoundException extends RuntimeException {

    public InstructorNotFoundException(String message) {
        super(message);
    }

    public InstructorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
