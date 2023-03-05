package com.softteam.vocabuilder.util.validations;

import com.softteam.vocabuilder.exections.VocabuilderIllegalArgumentException;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Data
@Component
public class Validations {
    public UUID validateUUIDType(String parameter) {
        UUID uuidText = new UUID(0L,0L);
        try {
            uuidText = UUID.fromString(parameter);
        } catch (Exception e) {
            throw new VocabuilderIllegalArgumentException("validation - the id provided is not valid", HttpStatus.NOT_FOUND);
        }
        return uuidText;
    }
}
