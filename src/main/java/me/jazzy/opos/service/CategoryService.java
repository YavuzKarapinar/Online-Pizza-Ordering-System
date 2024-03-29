package me.jazzy.opos.service;

import lombok.AllArgsConstructor;
import me.jazzy.opos.model.Category;
import me.jazzy.opos.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Category not found."));
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        categoryRepository.save(category);
        return category;
    }
}