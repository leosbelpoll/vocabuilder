package com.softteam.vocabuilder.controller;

import com.softteam.vocabuilder.persistence.entity.Category;
import com.softteam.vocabuilder.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.MapsId;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/categories")

public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        categoryService.create(category);
        return new ResponseEntity<Category>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") UUID id, @RequestBody Category category){
        category.setId(id);
        categoryService.update(category);
        return new ResponseEntity<Category>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable(value = "id") UUID id){
        Optional<Category> category = categoryService.read(id);
        return new ResponseEntity<Optional<Category>>(category,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(value = "id") UUID id){
        categoryService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
