package com.piphub.springjwtauthentication.services;


import com.piphub.springjwtauthentication.models.Category;
import com.piphub.springjwtauthentication.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllCategoryByActive() {
        return categoryRepository.findAllByStatus("ACT");
    }

    @Override
    public void create(Category req) {
        req.setId(0);
        categoryRepository.save(req);
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Category req) {
        categoryRepository.save(req);
    }

    @Override
    public void delete(Category req) {
        req.setStatus("DEL");
        categoryRepository.save(req);
    }


}
