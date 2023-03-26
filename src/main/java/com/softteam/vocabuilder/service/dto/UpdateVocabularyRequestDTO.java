package com.softteam.vocabuilder.service.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateVocabularyRequestDTO {
    @NotNull(message = "Title is required")
    private String title;
    @NotNull(message = "Description is required")
    private String description;
}
