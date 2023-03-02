package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.exections.CategoryNotFoundException;
import com.softteam.vocabuilder.persistence.entity.Category;
import com.softteam.vocabuilder.persistence.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {
        Optional<Category> category1 = categoryRepository.findById(category.getId());
        if(category1.isEmpty()){
            throw new CategoryNotFoundException("category not found",HttpStatus.NOT_FOUND);
        }
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> read(UUID id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException("category not found",HttpStatus.NOT_FOUND);
        }
        return optionalCategory;
    }

    @Override
    public void delete(UUID id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException("category not found",HttpStatus.NOT_FOUND);
        }
        categoryRepository.deleteById(id);
    }
}
