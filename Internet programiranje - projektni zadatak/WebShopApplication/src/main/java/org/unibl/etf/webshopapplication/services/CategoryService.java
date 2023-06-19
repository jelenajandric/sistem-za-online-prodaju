package org.unibl.etf.webshopapplication.services;

import org.springframework.stereotype.Service;
import org.unibl.etf.webshopapplication.model.entities.CategoryEntity;
import org.unibl.etf.webshopapplication.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> getAllCategories() {
        return categoryRepository.findAll();
    }
}
