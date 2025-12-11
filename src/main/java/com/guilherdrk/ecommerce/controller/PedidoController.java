package com.guilherdrk.ecommerce.controller;

import com.guilherdrk.ecommerce.dto.ApiResponseDTO;
import com.guilherdrk.ecommerce.dto.CreateOrderDTO;
import com.guilherdrk.ecommerce.dto.OrderSummaryDTO;
import com.guilherdrk.ecommerce.dto.PaginationResponseDTO;
import com.guilherdrk.ecommerce.entities.OrderEntity;
import com.guilherdrk.ecommerce.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

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


    @GetMapping
    public ResponseEntity<ApiResponseDTO<OrderSummaryDTO>> listOrders(@RequestParam(name = "page", defaultValue = "0" ) Integer page,
                                                                      @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize){
        var response = orderService.findAll(page, pageSize);
        return ResponseEntity.ok(
                new ApiResponseDTO<>(
                    response.getContent(),
                        new PaginationResponseDTO(response.getNumber(),
                                response.getSize(),
                                response.getTotalElements(),
                                response.getTotalPages())
                )
        );


    }

}
