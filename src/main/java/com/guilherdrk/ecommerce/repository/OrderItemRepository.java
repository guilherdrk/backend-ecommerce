package com.guilherdrk.ecommerce.repository;

import com.guilherdrk.ecommerce.entities.OrderItemEntity;
import com.guilherdrk.ecommerce.entities.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemId> {
}
