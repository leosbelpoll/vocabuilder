package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.exections.ResourceNotFoundException;
import com.softteam.vocabuilder.persistence.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICategoryService {
    Category create(Category category);

    Category update(Category category);

    Category partialUpdate(Category category) throws ResourceNotFoundException;

    Category getCategory(UUID id) throws ResourceNotFoundException;

    List<Category> findAllCategories();

    void delete(UUID id);
}
