package com.guilherdrk.ecommerce.repository;

import com.guilherdrk.ecommerce.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
