package com.softteam.vocabuilder.service;

import com.softteam.vocabuilder.persistence.entity.Category;
import com.softteam.vocabuilder.persistence.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        System.out.println("Service");
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> read(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
            //thorw new
        }
        categoryRepository.deleteById(id);
    }
}
