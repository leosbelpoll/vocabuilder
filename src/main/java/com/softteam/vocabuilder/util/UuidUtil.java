package com.softteam.vocabuilder.util;

import com.softteam.vocabuilder.exections.VocabuilderIllegalArgumentException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class UuidUtil {
    public static UUID getUUID(String parameter) {
        UUID uuidText = new UUID(0L,0L);
        try {
            uuidText = UUID.fromString(parameter);
        } catch (Exception e) {
            throw new VocabuilderIllegalArgumentException("validation - the id provided is not valid");
        }
        return uuidText;
    }
}
