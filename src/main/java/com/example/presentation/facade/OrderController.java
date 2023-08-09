package com.example.presentation.facade;

import com.example.application.service.OrderApplicationService;
import com.example.presentation.vo.OrderDto;
import com.example.presentation.vo.OrderRequestDto;
import com.example.presentation.vo.OrderResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {

  private final OrderApplicationService orderApplicationService;

  @PostMapping
  public OrderResponseDto createOrder(@Validated @RequestBody OrderRequestDto orderRequestDto) {
    return orderApplicationService.createOrder(orderRequestDto);
  }

  @GetMapping
  public List<OrderDto> findAll(@RequestParam String customerId) {
    return orderApplicationService.findAll(customerId);
  }
}
