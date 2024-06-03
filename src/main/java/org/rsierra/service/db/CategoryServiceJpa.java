package org.rsierra.service.db;

import org.rsierra.models.Category;
import org.rsierra.repository.CategoryRepository;
import org.rsierra.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CategoryServiceJpa implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public void delete(Integer idCategory) {
        categoryRepository.deleteById(idCategory);
    }


}
