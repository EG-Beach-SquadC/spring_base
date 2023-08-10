package com.example.infrastructure.persistence.repository.domain;

import com.example.common.exception.NotFoundException;
import com.example.domain.entity.Order;
import com.example.infrastructure.persistence.assembler.OrderDataMapper;
import com.example.infrastructure.persistence.repository.JpaOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.example.constants.OrderFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderDomainRepositoryTest {
  @InjectMocks
  private OrderDomainRepository orderDomainRepository;

  @Mock
  private JpaOrderRepository jpaOrderRepository;

  private final OrderDataMapper mapper = OrderDataMapper.mapper;

  @Test
  void should_return_all_orders_when_orders_exist_in_db() {
    when(jpaOrderRepository.findAllByCustomerId(CUSTOMER_ID))
        .thenReturn(List.of(ORDER_PO1, ORDER_PO2));

    List<Order> orders = orderDomainRepository.findAllByCustomerId(CUSTOMER_ID);

    assertAll(() -> assertEquals(2, orders.size()),
        () -> assertEquals("id-1", orders.get(0).getId()),
        () -> assertEquals("id-2", orders.get(1).getId()));

    verify(jpaOrderRepository, times(1)).findAllByCustomerId(CUSTOMER_ID);
  }

  @Test
  void should_return_empty_order_list_when_no_order_in_db() {
    when(jpaOrderRepository.findAllByCustomerId(CUSTOMER_ID)).thenReturn(List.of());

    List<Order> orders = orderDomainRepository.findAllByCustomerId(CUSTOMER_ID);

    assertTrue(orders.isEmpty());

    verify(jpaOrderRepository, times(1)).findAllByCustomerId(CUSTOMER_ID);
  }

  @Test
  void should_return_corresponding_order_when_order_exists() {
    String orderId = "id-1";
    when(jpaOrderRepository.findById(orderId)).thenReturn(Optional.of(ORDER_PO1));

    Order order = orderDomainRepository.findByOrderId(orderId);

    assertEquals("id-1", order.getId());
    verify(jpaOrderRepository, times(1)).findById(orderId);
  }

  @Test
  void should_throw_not_found_exception_when_order_not_exist() {
    String orderId = "id-1";
    when(jpaOrderRepository.findById(orderId)).thenReturn(Optional.empty());

    assertThrows(NotFoundException.class, () -> orderDomainRepository.findByOrderId(orderId));
  }

  @Test
  void should_save_order_successfully() {
    when(jpaOrderRepository.save(ORDER_PO1)).thenReturn(ORDER_PO1);

    orderDomainRepository.save(ORDER_PO1);

    verify(jpaOrderRepository, times(1)).save(ORDER_PO1);
  }

}
