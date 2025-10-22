package com.sofipguz.Challenge5.controller;

import com.sofipguz.Challenge5.module.Order;
import com.sofipguz.Challenge5.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order Management API", description = "API for creating, retrieving, updating, and deleting orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Story 1: Placing an Order (POST)
    @Operation(summary = "Create a new order", description = "Enter a new order into the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order successfully created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid entry data", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    // Story 5: Viewing All Orders (GET)
    @Operation(summary = "Get a list of all orders", description = "Obtain a list of all orders in the system")
    @ApiResponse(responseCode = "200", description = "List successfully created")
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Story 2: Viewing a Placed Order (GET by ID)
    @Operation(summary = "Created a order by ID", description = "Get the details of a specific order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order found"),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(
            @Parameter(description = "Get a single order by its ID") @PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // Story 3 & 6: Updating an Order (PUT)
    @Operation(summary = "Update an existing order", description = "Actualiza los detalles de una orden, como la dirección o el estado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order successfully updated"),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(
            @Parameter(description = "ID of the order you wish to update") @PathVariable Long id,
            @RequestBody Order orderDetails) {
        Order updatedOrder = orderService.updateOrder(id, orderDetails);
        return ResponseEntity.ok(updatedOrder);
    }

    // Story 4: Canceling an Order (DELETE)
    @Operation(summary = "Delete an order", description = "Delete an order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Order successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Order not found", content = @Content)
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(
            @Parameter(description = "ID of the order to be deleted") @PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
