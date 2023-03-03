package com.softteam.vocabuilder.util.validations;

import lombok.Data;

import java.util.UUID;

@Data
public class Validations {
    public UUID validateUUIDType(String parameter) {
        try {
            UUID.fromString(parameter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return UUID.fromString(parameter);
    }
}
