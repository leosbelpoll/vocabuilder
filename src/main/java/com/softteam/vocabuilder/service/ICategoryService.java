package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.persistence.entity.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICategoryService {

    Category create(Category category);

    void update(Category category);

    Optional<Category> getCategory(String id);

    List<Category> findAllCategories();

    void delete(String id);

}
