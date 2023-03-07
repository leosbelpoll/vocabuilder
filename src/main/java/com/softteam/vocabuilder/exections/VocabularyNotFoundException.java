package com.softteam.vocabuilder.exections;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class VocabularyNotFoundException extends RuntimeException {
    private String message;

    private HttpStatus httpStatus;

    public VocabularyNotFoundException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
