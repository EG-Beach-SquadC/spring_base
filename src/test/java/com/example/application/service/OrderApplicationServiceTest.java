package com.example.application.service;

import com.example.domain.repository.OrderRepository;
import com.example.domain.repository.ProductRepository;
import com.example.infrastructure.persistence.entity.OrderPo;
import com.example.presentation.vo.OrderDto;
import com.example.presentation.vo.OrderResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.constants.OrderFixture.*;
import static com.example.constants.ProductFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OrderApplicationServiceTest {

  @InjectMocks
  private OrderApplicationService orderApplicationService;

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private ProductRepository productRepository;

  @Captor
  private ArgumentCaptor<OrderPo> captor;

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
    when(productRepository.findProductByID(any())).thenReturn(List.of(PRODUCT_PO1));
    when(orderRepository.save(any())).thenReturn(ORDER1);

    OrderResponseDto orderResponse = orderApplicationService.createOrder(ORDER_REQUEST_1);

    assertEquals("id-1", orderResponse.getId());
    verify(orderRepository, times(1)).save(captor.capture());
    String expected = "[{\"id\":\"id-1\",\"name\":\"customer-1\",\"price\":1,\"amount\":1}]";
    assertEquals(expected, captor.getValue().getProducts());
  }

  @Test
  void should_return_corresponding_order_given_valid_order_id() {
    String orderId = "id-1";
    when(orderRepository.findByOrderId(orderId)).thenReturn(ORDER1);

    OrderDto orderDto = orderApplicationService.retrieveOrder(orderId);

    assertEquals("id-1", orderDto.getId());
    verify(orderRepository, times(1)).findByOrderId(any());
  }

  @Test
  void should_return_false_when_give_a_product_list_contain_invalid_one() {
    when(productRepository.findProductByID(any())).thenReturn(List.of(PRODUCT_PO_INVALID));

    assertThrows(RuntimeException.class,
        () -> orderApplicationService.createOrder(ORDER_REQUEST_INVALID));
  }

  @Test
  void should_return_false_when_give_a_product_list_contain_invalid_product_id() {
    when(productRepository.findProductByID(any())).thenReturn(List.of(PRODUCT_PO2));

    assertThrows(RuntimeException.class,
        () -> orderApplicationService.createOrder(ORDER_REQUEST_INVALID));
  }

  @Test
  void should_return_false_when_give_a_product_list_contain_no_price_product() {
    when(productRepository.findProductByID(any()))
        .thenReturn(List.of(PRODUCT_PO1, PRODUCT_PO_WITHOUT_PRICE));

    assertThrows(RuntimeException.class,
        () -> orderApplicationService.createOrder(ORDER_REQUEST_INVALID));
  }
}
