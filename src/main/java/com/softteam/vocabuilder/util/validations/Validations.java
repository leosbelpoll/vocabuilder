package com.softteam.vocabuilder.util.validations;

import lombok.Data;

import java.util.UUID;

@Data
public class Validations {
    public UUID validateUUIDType(String parameter) {
        UUID uuidText = new UUID(0L,0L);
        try {
            uuidText = UUID.fromString(parameter);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return uuidText;
    }
}
