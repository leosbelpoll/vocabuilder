package com.softteam.vocabuilder.exections;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CategoryNotFoundException extends RuntimeException{
    private String message;

    private HttpStatus httpStatus;

    public CategoryNotFoundException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
