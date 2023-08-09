package com.example.presentation.facade;

import com.example.application.service.OrderService;
import com.example.presentation.vo.OrderRequestDto;
import com.example.presentation.vo.OrderResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDto createOrder(@Validated @RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }
}
