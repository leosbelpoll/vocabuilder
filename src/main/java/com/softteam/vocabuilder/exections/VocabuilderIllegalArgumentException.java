package com.softteam.vocabuilder.exections;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class VocabuilderIllegalArgumentException extends RuntimeException{
    private String message;


    public VocabuilderIllegalArgumentException(String message) {
        this.message = message;
    }
}
