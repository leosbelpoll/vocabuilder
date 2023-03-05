package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.exections.CategoryNotFoundException;
import com.softteam.vocabuilder.persistence.entity.Category;
import com.softteam.vocabuilder.persistence.repository.CategoryRepository;
import com.softteam.vocabuilder.util.validations.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Validations validations;

    @Override
    public Category create(Category category) {
        categoryRepository.save(category);
        List<Category> categoryList = categoryRepository.findAll();
        System.out.println("categoryList"+category.getId());
        return category;
    }

    @Override
    public void update(Category category) {
        try {
            categoryRepository.save(category);
        }catch (Exception e){
            throw new CategoryNotFoundException("category not found",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Optional<Category> getCategory(String id) {
        UUID uuidID = validations.validateUUIDType(id);
        Optional<Category> optionalCategory = categoryRepository.findById(uuidID);
        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException("category not found",HttpStatus.NOT_FOUND);
        }
        return optionalCategory;
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void delete(String id) {
        UUID uuidID = validations.validateUUIDType(id);
        Optional<Category> optionalCategory = categoryRepository.findById(uuidID);
        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException("category not found",HttpStatus.NOT_FOUND);
        }
        categoryRepository.deleteById(uuidID);
    }
}
