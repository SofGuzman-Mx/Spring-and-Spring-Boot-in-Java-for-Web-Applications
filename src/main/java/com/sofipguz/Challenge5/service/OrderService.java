package com.sofipguz.Challenge5.service;

import com.sofipguz.Challenge5.module.Order;
import com.sofipguz.Challenge5.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    // MÉTODO QUE FALTA: Para obtener todas las órdenes
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Método para obtener una orden por su ID
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    // Método para crear una orden (ya lo teníamos)
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    // Método para actualizar una orden
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = getOrderById(id); // Reutilizamos el método de arriba

        if (orderDetails.getCustomerName() != null) {
            order.setCustomerName(orderDetails.getCustomerName());
        }

        if (orderDetails.getShippingAddress() != null) {
            order.setShippingAddress(orderDetails.getShippingAddress());
        }

        if (orderDetails.getTotalPrice() != null) {
            order.setTotalPrice(orderDetails.getTotalPrice());
        }

        if(orderDetails.getStatus() != null) {
            order.setStatus(orderDetails.getStatus());
        }

        return orderRepository.save(order);
    }

    // Metodo para eliminar una orden
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
}