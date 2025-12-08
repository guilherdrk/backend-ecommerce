package com.guilherdrk.ecommerce.controller;

import com.guilherdrk.ecommerce.dto.CreateOrderDTO;
import com.guilherdrk.ecommerce.entities.OrderEntity;
import com.guilherdrk.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(path = "/orders")
public class PedidoController {


    private final OrderService orderService;

    public PedidoController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderDTO dto){


        var order = orderService.createOrder(dto);

        return ResponseEntity.created(URI.create("/orders/" + order.getOrderId())).build();
    }
}
