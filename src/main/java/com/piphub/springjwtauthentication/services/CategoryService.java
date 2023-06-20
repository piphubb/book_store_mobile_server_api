package com.piphub.springjwtauthentication.services;


import com.piphub.springjwtauthentication.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAllCategory();

    List<Category> findAllCategoryByActive();

    void create(Category req);

    Category findById(Integer id);

    void update(Category req);

    void delete(Category req);
}
