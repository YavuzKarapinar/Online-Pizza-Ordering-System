package me.jazzy.opos.service;

import lombok.AllArgsConstructor;
import me.jazzy.opos.model.Category;
import me.jazzy.opos.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Category not found."));
    }
}