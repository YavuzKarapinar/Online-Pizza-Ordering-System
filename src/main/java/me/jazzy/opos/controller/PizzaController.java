package me.jazzy.opos.controller;

import lombok.AllArgsConstructor;
import me.jazzy.opos.dto.PizzaDto;
import me.jazzy.opos.model.Pizza;
import me.jazzy.opos.service.PizzaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pizzas")
@AllArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<List<Pizza>> getAllPizzas() {
        return new ResponseEntity<>(pizzaService.getAllPizzas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> getPizzaById(@PathVariable Long id) {
        return new ResponseEntity<>(pizzaService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pizza> getPizzaById(@RequestBody PizzaDto pizzaDto) {
        return new ResponseEntity<>(pizzaService.savePizza(pizzaDto), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Pizza> getPizzaById(@RequestBody Pizza pizza) {
        return new ResponseEntity<>(pizzaService.updatePizza(pizza), HttpStatus.OK);
    }
}