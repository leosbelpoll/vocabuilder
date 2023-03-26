package com.softteam.vocabuilder.service.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

import java.util.UUID;

@Data
public class CategoryDTO {

    private UUID id;

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private String color;
}
