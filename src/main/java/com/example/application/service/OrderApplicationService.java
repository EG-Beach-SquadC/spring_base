package com.example.application.service;

import com.example.application.assembler.OrderDtoMapper;
import com.example.domain.repository.OrderRepository;
import com.example.presentation.vo.OrderDto;
import com.example.presentation.vo.OrderRequestDto;
import com.example.presentation.vo.OrderResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderApplicationService {

  private final OrderRepository orderRepository;

  private final OrderDtoMapper orderDtoMapper = OrderDtoMapper.MAPPER;

  public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
    // return
    // orderDtoMapper.toDto(orderRepository.createOrder(orderDtoMapper.toPo(orderRequestDto)));
    return null;
  }

  public List<OrderDto> findAll(String customerId) {
    return orderRepository.findAll(customerId).stream().map(orderDtoMapper::toDto)
        .collect(Collectors.toList());
  }
}
