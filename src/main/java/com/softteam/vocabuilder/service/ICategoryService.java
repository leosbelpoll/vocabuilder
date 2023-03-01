package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.persistence.entity.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    Category create(Category category);

    void update(Category category);

    Optional<Category> read(Long id);

    void delete(Long id);

}
