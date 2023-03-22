package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.exections.CategoryNotFoundException;
import com.softteam.vocabuilder.exections.ResourceNotFoundException;
import com.softteam.vocabuilder.persistence.entity.Category;
import com.softteam.vocabuilder.persistence.repository.CategoryRepository;
import com.softteam.vocabuilder.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    @Override
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategory(UUID id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("category not found");
        }
        return optionalCategory;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("category not found");
        }
        categoryRepository.deleteById(id);
    }
}
