package com.sofipguz.Challenge5.repository;

import com.sofipguz.Challenge5.module.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderRepository extends JpaRepository<Order, Long>  {
}
