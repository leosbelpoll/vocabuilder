package com.softteam.vocabuilder.controller;

import com.softteam.vocabuilder.exections.CategoryNotFoundException;
import com.softteam.vocabuilder.exections.ResourceNotFoundException;
import com.softteam.vocabuilder.persistence.entity.Category;
import com.softteam.vocabuilder.service.CategoryServiceImpl;
import com.softteam.vocabuilder.service.dto.CategoryDTO;
import com.softteam.vocabuilder.service.dto.UpdateCategoryRequestDTO;
import com.softteam.vocabuilder.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody @Validated CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setTitle(categoryDTO.getTitle());
        category.setDescription(categoryDTO.getDescription());
        category.setColor(categoryDTO.getColor());
        category.setCreatedAt(new Date());
        category.setUpdatedAt(new Date());

        Category newCategory = categoryService.create(category);
        return new ResponseEntity<Category>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable(value = "id") String id, @RequestBody @Validated CategoryDTO categoryDTO) {
        UUID uuid = UuidUtil.getUUID(id);

        Category category = new Category();
        category.setTitle(categoryDTO.getTitle());
        category.setDescription(categoryDTO.getDescription());
        category.setColor(categoryDTO.getColor());
        category.setId(uuid);

        try {
            Category updateCategory = categoryService.update(category);
            return new ResponseEntity<Category>(updateCategory, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("Oops, we couldn't find the category", HttpStatus.BAD_REQUEST);
        } catch (RuntimeException exception) {
            return new ResponseEntity<>("Sorry, something wrong happened, we're working to solve it", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateCategory(@PathVariable(value = "id") String id, @RequestBody UpdateCategoryRequestDTO categoryDTO) {
        UUID uuid = UuidUtil.getUUID(id);

        Category category = new Category();
        category.setTitle(categoryDTO.getTitle());
        category.setDescription(categoryDTO.getDescription());
        category.setColor(categoryDTO.getColor());
        category.setId(uuid);

        try {
            Category updateCategory = categoryService.partialUpdate(category);
            return new ResponseEntity<Category>(updateCategory, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>("Oops, we couldn't find the category", HttpStatus.BAD_REQUEST);
        } catch (RuntimeException exception) {
            return new ResponseEntity<>("Sorry, something wrong happened, we're working to solve it", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable(value = "id") String id) {
        UUID uuid = UuidUtil.getUUID(id);
        Category category = new Category();

        try {
            category = categoryService.getCategory(uuid);
            return new ResponseEntity<Category>(category, HttpStatus.OK);
        } catch (ResourceNotFoundException exception) {
            throw new CategoryNotFoundException("Oops, we couldn't find the category", HttpStatus.NOT_FOUND);
        }
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
            throw new CategoryNotFoundException("Oops, we couldn't find the category", HttpStatus.NOT_FOUND);
        }
    }
}
