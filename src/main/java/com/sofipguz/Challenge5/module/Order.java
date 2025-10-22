package com.sofipguz.Challenge5.module;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data; // From Lombok dependency

@Data // Creates getters, setters, toString, etc. automatically
@Entity
@Table(name = "orders") // Specifies the table name in the database
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String shippingAddress;
    private Double totalPrice;
    private String status; // For user story R5-6
}