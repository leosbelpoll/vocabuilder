package com.softteam.vocabuilder.persisntece.entities;

import com.softteam.vocabuilder.persistence.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category;

    @BeforeEach
    void init() {
        category = new Category();
        category.setId(new UUID(1L, 1L));
        category.setTitle("title");
        category.setDescription("description");
        category.setColor("color");
    }

    @Test
    void shouldHaveCorretInfo() {
        assertEquals("title", category.getTitle());
        assertEquals("description", category.getDescription());
        assertEquals("color", category.getColor());
    }
}
