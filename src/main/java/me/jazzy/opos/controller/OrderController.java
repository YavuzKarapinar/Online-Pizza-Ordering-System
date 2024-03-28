package me.jazzy.opos.controller;

import lombok.AllArgsConstructor;
import me.jazzy.opos.dto.OrderDto;
import me.jazzy.opos.model.Order;
import me.jazzy.opos.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}/{userId}")
    public ResponseEntity<Order> getSpecificOrder(@PathVariable Long id, @PathVariable Long userId) {
        return new ResponseEntity<>(orderService.getSpecificOrderByIds(userId, id), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Order>> getOrderByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(orderService.getOrderListByUserId(userId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrder() {
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> saveOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.saveOrder(orderDto), HttpStatus.CREATED);
    }

}