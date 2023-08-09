package com.example.application.service;

import com.example.domain.repository.OrderRepository;
import com.example.presentation.vo.OrderDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.constants.ProductFixture.CUSTOMER_ID;
import static com.example.constants.ProductFixture.ORDER1;
import static com.example.constants.ProductFixture.ORDER2;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class OrderApplicationServiceTest {

  @InjectMocks
  private OrderApplicationService orderApplicationService;

  @Mock
  private OrderRepository orderRepository;

  @Test
  void should_return_response_dto_successfully() {
    // // given
    // Product product1 = Product.builder().id("1").name("product
    // 1").price(BigDecimal.ONE).status(ProductStatus.VALID).build();
    // Product product2 = Product.builder().id("2").name("product
    // 2").price(BigDecimal.ONE).status(ProductStatus.VALID).build();
    // List<Product> products = List.of(product1, product2);
    // OrderRequestDto orderRequestDto =
    // OrderRequestDto.builder().customerId("1").products(products).build();
    // when(orderRepository.createOrder(OrderDtoMapper.MAPPER.toPo(orderRequestDto)))
    // .thenReturn(Order.builder().id("1")
    // .customerId(orderRequestDto.getCustomerId())
    // .products(products)
    // .total(BigDecimal.valueOf(2.00))
    // .status(OrderStatus.CREATED).build());
    //
    // // when
    // OrderResponseDto order = orderService.createOrder(orderRequestDto);
    //
    // // then
    // Assertions.assertEquals(order, orderRequestDto);

  }

  @Test
  void should_return_all_products_when_products_exist_in_repo() {
    when(orderRepository.findAll(CUSTOMER_ID)).thenReturn(List.of(ORDER1, ORDER2));

    List<OrderDto> orderDtos = orderApplicationService.findAll(CUSTOMER_ID);

    assertAll(() -> assertEquals(2, orderDtos.size()),
        () -> assertEquals("id-1", orderDtos.get(0).getId()),
        () -> assertEquals("id-2", orderDtos.get(1).getId()));
  }

}
