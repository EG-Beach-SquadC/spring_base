package com.example.application.service;

import com.example.application.assembler.OrderDtoMapper;
import com.example.domain.entity.Order;
import com.example.domain.entity.OrderStatus;
import com.example.domain.repository.OrderRepository;
import com.example.domain.repository.ProductRepository;
import com.example.infrastructure.persistence.assembler.OrderDataMapper;
import com.example.presentation.vo.OrderDto;
import com.example.presentation.vo.OrderRequestDto;
import com.example.presentation.vo.OrderResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderApplicationService {

  private final OrderRepository orderRepository;

  private final ProductRepository productRepository;

  private final OrderDtoMapper orderDtoMapper = OrderDtoMapper.mapper;

  private final OrderDataMapper orderDataMapper = OrderDataMapper.mapper;

  public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
    Order order = orderDtoMapper.requestDtoToDo(orderRequestDto);
    BigDecimal total = productRepository.countTotal(orderRequestDto.getProducts());

    order.setTotal(total);
    order.setStatus(OrderStatus.CREATED);
    return orderDtoMapper.doToResponseDto(orderRepository.save(orderDataMapper.toPo(order)));
  }

  public List<OrderDto> retrieveOrders(String customerId) {
    return orderRepository.findAllByCustomerId(customerId).stream().map(orderDtoMapper::toDto)
        .collect(Collectors.toList());
  }

  public OrderDto retrieveOrder(String orderId) {
    return orderDtoMapper.toDto(orderRepository.findByOrderId(orderId));
  }
}
