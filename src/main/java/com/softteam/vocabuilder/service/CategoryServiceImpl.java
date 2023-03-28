package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.exections.ResourceNotFoundException;
import com.softteam.vocabuilder.persistence.entity.Category;
import com.softteam.vocabuilder.persistence.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
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
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("category not found");
        }

        Category foundCategory = optionalCategory.get();
        foundCategory.setTitle(category.getTitle());
        foundCategory.setDescription(category.getDescription());
        foundCategory.setColor(category.getColor());
        foundCategory.setUpdatedAt(new Date());

        return categoryRepository.save(foundCategory);
    }

    @Override
    public Category partialUpdate(Category category) throws ResourceNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("category not found");
        }

        Category foundCategory = optionalCategory.get();
        if (category.getTitle() != null) {
            foundCategory.setTitle(category.getTitle());
        }
        if (category.getDescription() != null) {
            foundCategory.setDescription(category.getDescription());
        }
        if (category.getColor() != null) {
            foundCategory.setColor(category.getColor());
        }
        foundCategory.setUpdatedAt(new Date());

        return categoryRepository.save(foundCategory) ;
    }

    @Override
    public Category getCategory(UUID id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("category not found");
        }

        return optionalCategory.get();
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
