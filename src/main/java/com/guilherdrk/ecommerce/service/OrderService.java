package com.guilherdrk.ecommerce.service;

import com.guilherdrk.ecommerce.dto.CreateOrderDTO;
import com.guilherdrk.ecommerce.dto.OrderItemDTO;
import com.guilherdrk.ecommerce.dto.OrderSummaryDTO;
import com.guilherdrk.ecommerce.entities.*;
import com.guilherdrk.ecommerce.exception.CreateOrderException;
import com.guilherdrk.ecommerce.repository.OrderItemRepository;
import com.guilherdrk.ecommerce.repository.OrderRepository;
import com.guilherdrk.ecommerce.repository.ProductRepository;
import com.guilherdrk.ecommerce.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public OrderService(UserRepository userRepository,
                        OrderRepository orderRepository,
                        ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderEntity createOrder(CreateOrderDTO dto){
        var order = new OrderEntity();

        //1. Validade user
        var user = validateUser(dto);
        //2. validate order Items
        var orderItems = validateOrderItems(order, dto);
        //3. Calculate order total
        var total = calculateOrderTotal(orderItems);
        //4. Map to entity
        order.setOrderDate(LocalDateTime.now());
        order.setUser(user);
        order.setItems(orderItems);
        order.setTotal(total);
        //5. Repository save
        return orderRepository.save(order);

    }

    private UserEntity validateUser(CreateOrderDTO dto){
        return userRepository.findById(dto.userId())
                .orElseThrow(() -> new CreateOrderException("user not found"));
    }

    private List<OrderItemEntity> validateOrderItems(OrderEntity order, CreateOrderDTO dto) {
        if(dto.items().isEmpty()){
            throw new CreateOrderException("order items is empty");
        }
        return dto.items()
                .stream()
                .map(orderItemDTO -> getOrderItem(order, orderItemDTO))
                .toList();
    }

    private OrderItemEntity getOrderItem(OrderEntity order,
                                         OrderItemDTO orderItemDTO) {
        var orderItemEntity = new OrderItemEntity();
        var id = new OrderItemId();
        var product = getProduct(orderItemDTO.productId());

        id.setOrder(order);
        id.setProduct(product );

        orderItemEntity.setId(id);
        orderItemEntity.setQuantity(orderItemDTO.quantity());
        orderItemEntity.setSalePrice(product.getPrice());

        return orderItemEntity;
    }
    private ProductEntity getProduct(Long producId){
        return productRepository.findById(producId)
                .orElseThrow(() -> new CreateOrderException("product not found"));
    }

    private BigDecimal calculateOrderTotal(List<OrderItemEntity> items) {
        return items.stream()
                .map(i -> i.getSalePrice().multiply(BigDecimal.valueOf(i.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public Page<OrderSummaryDTO> findAll(Integer page, Integer pageSize) {
        return orderRepository.findAll(PageRequest.of(page, pageSize))
                .map(entity -> {
                    return new OrderSummaryDTO(
                            entity.getOrderId(),
                            entity.getOrderDate(),
                            entity.getUser().getUserId(),
                            entity.getTotal()
                    );
                });
    }
}
