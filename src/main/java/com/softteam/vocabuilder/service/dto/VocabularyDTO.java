package com.softteam.vocabuilder.service.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class VocabularyDTO {
    private UUID id;
    @NotNull(message = "Title is required")
    private String title;

    @NotNull(message = "Description is required")
    private String description;
}
