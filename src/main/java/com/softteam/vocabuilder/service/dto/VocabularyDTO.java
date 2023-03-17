package com.softteam.vocabuilder.service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class VocabularyDTO {
    private UUID id;
    private String title;
    private String description;
}
