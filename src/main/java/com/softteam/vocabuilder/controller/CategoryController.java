package com.softteam.vocabuilder.controller;

import com.softteam.vocabuilder.exections.CategoryNotFoundException;
import com.softteam.vocabuilder.exections.ResourceNotFoundException;
import com.softteam.vocabuilder.persistence.entity.Category;
import com.softteam.vocabuilder.service.CategoryServiceImpl;
import com.softteam.vocabuilder.service.dto.CategoryDTO;
import com.softteam.vocabuilder.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = new Category();
        if (categoryDTO.getTitle() == null || categoryDTO.getDescription() == null || categoryDTO.getColor() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        category.setTitle(categoryDTO.getTitle());
        category.setDescription(categoryDTO.getDescription());
        category.setColor(categoryDTO.getColor());
        category.setCreatedAt(new Date());
        category.setUpdatedAt(new Date());
        Category newCategory = categoryService.create(category);
        return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") String id, @RequestBody Category category) {
        UUID uuid = UuidUtil.getUUID(id);
        Optional<Category> newCategory = Optional.of(new Category());
        try {
            newCategory = categoryService.getCategory(uuid);
        } catch (ResourceNotFoundException e) {
            throw new CategoryNotFoundException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        if (category.getTitle() == null) {
            category.setTitle(newCategory.get().getTitle());
        }
        if (category.getDescription() == null) {
            category.setDescription(newCategory.get().getDescription());
        }
        if (category.getColor() == null) {
            category.setColor(newCategory.get().getColor());
        }
        category.setUpdatedAt(new Date());
        category.setCreatedAt(newCategory.get().getCreatedAt());

        category.setId(uuid);
        Category updateCategory = categoryService.update(category);
        return new ResponseEntity<Category>(updateCategory, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable(value = "id") String id) {
        UUID uuid = UuidUtil.getUUID(id);
        Optional<Category> optionalCategory = Optional.of(new Category());
        try {
            optionalCategory = categoryService.getCategory(uuid);
        } catch (ResourceNotFoundException e) {
            throw new CategoryNotFoundException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Category>>(optionalCategory, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Category>> listCategories() {
        return new ResponseEntity<List<Category>>(categoryService.findAllCategories(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(value = "id") String id) {
        UUID uuid = UuidUtil.getUUID(id);
        try {
            categoryService.delete(uuid);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            throw new CategoryNotFoundException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
