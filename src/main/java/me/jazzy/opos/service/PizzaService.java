package me.jazzy.opos.service;

import lombok.AllArgsConstructor;
import me.jazzy.opos.dto.PizzaDto;
import me.jazzy.opos.exception.notfound.PizzaNotFoundException;
import me.jazzy.opos.model.Category;
import me.jazzy.opos.model.Ingredient;
import me.jazzy.opos.model.Pizza;
import me.jazzy.opos.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final CategoryService categoryService;
    private final IngredientsService ingredientsService;

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public Pizza getById(Long id) {
        return  pizzaRepository.findById(id)
                    .orElseThrow(() -> new PizzaNotFoundException("Pizza not found."));
    }

    public Pizza savePizza(PizzaDto pizzaDto) {
        Ingredient ingredient = ingredientsService.findById(pizzaDto.getIngredientId());
        Category category = categoryService.findById(pizzaDto.getCategoryId());

        Pizza pizza = new Pizza(
                pizzaDto.getName(),
                pizzaDto.getMoney(),
                List.of(ingredient),
                category
        );

        pizzaRepository.save(pizza);

        return pizza;
    }

    public Pizza updatePizza(Pizza pizza) {
        getById(pizza.getId());

        pizzaRepository.save(pizza);

        return pizza;
    }

}