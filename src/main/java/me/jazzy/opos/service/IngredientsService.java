package me.jazzy.opos.service;

import lombok.AllArgsConstructor;
import me.jazzy.opos.model.Ingredient;
import me.jazzy.opos.repository.IngredientRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class IngredientsService {

    private final IngredientRepository ingredientRepository;

    public Ingredient findById(Long id) {
        return  ingredientRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Ingredient not found."));
    }

}