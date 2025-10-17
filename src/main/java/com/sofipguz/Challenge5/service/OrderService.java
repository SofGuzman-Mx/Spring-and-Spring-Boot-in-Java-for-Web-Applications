package com.sofipguz.Challenge5.service;

import com.sofipguz.Challenge5.module.Order;
import com.sofipguz.Challenge5.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // THIS IS THE METHOD CALLED BY THE CONTROLLER
    public Order createOrder(Order order) {
        return orderRepository.save (order); // (6)
    }

    public Order getOrderById(Long id) {
        return orderRepository.getReferenceById(id);
    }
    public Order updateOrder(Long id, Order orderDetails) {

        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            Order existingOrder = orderOptional.get();
            existingOrder.setCustomerName(orderDetails.getCustomerName());
            existingOrder.setProducts(orderDetails.getProducts());
            existingOrder.setTotalAmount(orderDetails.getTotalAmount());

            return orderRepository.save(existingOrder);
        } else {
            return null;
        }
    }


    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
}
