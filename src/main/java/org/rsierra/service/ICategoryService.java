package org.rsierra.service;

import org.rsierra.models.Category;

import java.util.List;

public interface ICategoryService {
    void addCategory(Category category);
    List<Category> getCategories();
    Category getCategoryById(int id);
}
