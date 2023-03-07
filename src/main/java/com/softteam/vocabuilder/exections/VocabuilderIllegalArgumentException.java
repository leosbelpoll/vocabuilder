package com.softteam.vocabuilder.exections;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class VocabuilderIllegalArgumentException extends RuntimeException{
    private String message;

    private HttpStatus httpStatus;

    public VocabuilderIllegalArgumentException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
