package com.sofipguz.Challenge5.repository;

import com.sofipguz.Challenge5.module.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>  {
    // Spring Data JPA provides methods like findAll(), findById(), save(), deleteById()
    // so we don't have to write any code here!
}


