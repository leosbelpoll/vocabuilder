package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.persistence.entity.Category;

import java.util.List;

public interface ICategoryService {

    Category create(Category category);

    Category update(Category category);

    Category read(Long id);

    Category delete(Long id);

}
