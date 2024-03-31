package me.jazzy.opos.service;

import lombok.AllArgsConstructor;
import me.jazzy.opos.dto.OrderDto;
import me.jazzy.opos.exception.notfound.OrderNotFoundException;
import me.jazzy.opos.model.Cart;
import me.jazzy.opos.model.Order;
import me.jazzy.opos.model.OrderStatus;
import me.jazzy.opos.model.User;
import me.jazzy.opos.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CartService cartService;

    public Order getSpecificOrderByIds(Long userId, Long id) {
        return orderRepository.findSpecificOrderByIds(userId, id)
                    .orElseThrow(() -> new OrderNotFoundException("User or Orders not found."));
    }

    public List<Order> getOrderListByUserId(Long userId) {
        return orderRepository.findByUserId(userId)
                    .orElseThrow(() -> new OrderNotFoundException("User or Orders not found."));
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(OrderDto orderDto) {
        User user = userService.getUserById(orderDto.getUserId());
        Cart cart = cartService.getCartById(orderDto.getCartId());

        Order order = new Order(
                user,
                OrderStatus.PENDING,
                LocalDateTime.now(),
                cart
        );

        orderRepository.save(order);

        return order;
    }

}