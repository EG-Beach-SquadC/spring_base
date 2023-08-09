package com.example.application.service;

import com.example.application.assembler.OrderDtoMapper;
import com.example.domain.entity.Order;
import com.example.domain.entity.OrderStatus;
import com.example.domain.entity.Product;
import com.example.domain.entity.ProductStatus;
import com.example.domain.repository.OrderRepository;
import com.example.presentation.vo.OrderRequestDto;
import com.example.presentation.vo.OrderResponseDto;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.powermock.api.mockito.PowerMockito.when;


@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void should_return_response_dto_successfully() {
        // given
        Product product1 = Product.builder().id("1").name("product 1").price(BigDecimal.ONE).status(ProductStatus.VALID).build();
        Product product2 = Product.builder().id("2").name("product 2").price(BigDecimal.ONE).status(ProductStatus.VALID).build();
        List<Product> products = List.of(product1, product2);
        OrderRequestDto orderRequestDto = OrderRequestDto.builder().customerId("1").products(products).build();
        when(orderRepository.createOrder(OrderDtoMapper.MAPPER.toPo(orderRequestDto)))
                .thenReturn(Order.builder().id("1")
                        .customerId(orderRequestDto.getCustomerId())
                        .products(products)
                        .total(BigDecimal.valueOf(2.00))
                        .status(OrderStatus.CREATED).build());

        // when
        OrderResponseDto order = orderService.createOrder(orderRequestDto);

        // then
        Assertions.assertEquals(order, orderRequestDto);

    }

}