package com.softteam.vocabuilder.service.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

import java.util.UUID;

@Data
public class CategoryDTO {

    private UUID id;

    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;
    @NotBlank(message = "Color is required")
    private String color;
}
