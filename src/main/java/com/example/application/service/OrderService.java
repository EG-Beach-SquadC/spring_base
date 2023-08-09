package com.example.application.service;

import com.example.application.assembler.OrderDtoMapper;
import com.example.domain.repository.OrderRepository;
import com.example.presentation.vo.OrderRequestDto;
import com.example.presentation.vo.OrderResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderDtoMapper orderDtoMapper;
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        return orderDtoMapper.toDto(orderRepository.createOrder(orderDtoMapper.toPo(orderRequestDto)));
    }
}
