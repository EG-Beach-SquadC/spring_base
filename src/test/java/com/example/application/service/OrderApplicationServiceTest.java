package com.example.application.service;

import com.example.application.assembler.OrderDtoMapper;
import com.example.domain.repository.OrderRepository;
import com.example.domain.repository.ProductRepository;
import com.example.infrastructure.persistence.assembler.OrderDataMapper;
import com.example.presentation.vo.OrderDto;
import com.example.presentation.vo.OrderResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.example.constants.OrderFixture.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OrderApplicationServiceTest {

  private final OrderDataMapper dataMapper = OrderDataMapper.mapper;

  private final OrderDtoMapper dtoMapper = OrderDtoMapper.mapper;

  @InjectMocks
  private OrderApplicationService orderApplicationService;

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private ProductRepository productRepository;

  @Test
  void should_return_all_orders_when_orders_exist_in_repo() {
    when(orderRepository.findAllByCustomerId(CUSTOMER_ID)).thenReturn(List.of(ORDER1, ORDER2));

    List<OrderDto> orderDtos = orderApplicationService.retrieveOrders(CUSTOMER_ID);

    assertAll(() -> assertEquals(2, orderDtos.size()),
        () -> assertEquals("id-1", orderDtos.get(0).getId()),
        () -> assertEquals("id-2", orderDtos.get(1).getId()));
  }

  @Test
  void should_create_order() {
    when(productRepository.countTotal(any())).thenReturn(BigDecimal.TEN);
    when(orderRepository.save(any())).thenReturn(ORDER1);

    OrderResponseDto orderResponse = orderApplicationService.createOrder(ORDER_REQUEST_1);

    assertEquals("id-1", orderResponse.getId());
  }

  @Test
  void should_return_corresponding_order_given_valid_order_id() {
    String orderId = "id-1";
    when(orderRepository.findByOrderId(orderId)).thenReturn(ORDER1);

    OrderDto orderDto = orderApplicationService.retrieveOrder(orderId);

    assertEquals("id-1", orderDto.getId());
    verify(orderRepository, times(1)).findByOrderId(any());
  }

}
