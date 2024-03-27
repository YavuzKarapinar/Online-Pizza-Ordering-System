package me.jazzy.opos.service;

import lombok.AllArgsConstructor;
import me.jazzy.opos.model.Pizza;
import me.jazzy.opos.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    public Pizza getById(Long id) {
        return  pizzaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Pizza not found."));
    }

}