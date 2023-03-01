package com.softteam.vocabuilder.controller;

import com.softteam.vocabuilder.persistence.entity.Category;
import com.softteam.vocabuilder.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.MapsId;
import java.util.Optional;

@RestController
@RequestMapping("/categories")

public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        categoryService.create(category);
        return new ResponseEntity<Category>(HttpStatus.CREATED);
    }
    @PatchMapping("/update/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long id, @RequestBody Category category){
        category.setId(id);
        categoryService.update(category);
        return new ResponseEntity<Category>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> readCategory(@PathVariable(value = "id") Long id){
        Optional<Category> category = categoryService.read(id);
        return new ResponseEntity<Optional<Category>>(category,HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable(value = "id") Long id){
        categoryService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }





}
