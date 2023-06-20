package com.piphub.springjwtauthentication.controllers.rest;

import com.piphub.springjwtauthentication.models.Category;
import com.piphub.springjwtauthentication.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/category")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> getAllCategories() {
        log.info("Intercept get all category");
        return new ResponseEntity<>(categoryService.findAllCategory(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody Category req) {
        log.info("Intercept create category {}",req);
        categoryService.create(req);
        return new ResponseEntity<>("Create Category Success", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategoryId(@PathVariable("id") Integer id) {
        log.info("Intercept find category by id {}",id);
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Category req) {
        log.info("Intercept update category {}",req);
        categoryService.update(req);
        return new ResponseEntity<>("Update Category Success", HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> delete(@RequestBody Category req) {
        log.info("Intercept delete category {}",req);
        categoryService.delete(req);
        return new ResponseEntity<>("Delete Category Success", HttpStatus.OK);
    }

}
