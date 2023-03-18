package com.softteam.vocabuilder.service.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.UUID;
@Data
public class CategoryDTO {
    private UUID id;
    private String title;

    private String description;

    private String color;
}
