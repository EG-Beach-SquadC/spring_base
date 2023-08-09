package com.example.infrastructure.persistence.repository.domain;

import com.example.domain.entity.Order;
import com.example.domain.repository.OrderRepository;
import com.example.infrastructure.persistence.assembler.OrderDataMapper;
import com.example.infrastructure.persistence.entity.OrderPo;
import com.example.infrastructure.persistence.repository.JpaOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.common.exception.BaseExceptionCode.NOT_FOUND_CUSTOMER;
import static com.example.common.exception.NotFoundException.notFoundException;

@Component
@AllArgsConstructor
public class OrderDomainRepository implements OrderRepository {
  private final JpaOrderRepository jpaOrderRepository;
  private final OrderDataMapper mapper = OrderDataMapper.mapper;

  @Override
  public Order createOrder(OrderPo orderPo) {
    return null;
  }

  @Override
  public List<Order> findAllByCustomerId(String customerId) {
    return jpaOrderRepository.findAllByCustomerId(customerId).stream().map(mapper::toDo)
        .collect(Collectors.toList());
  }

  @Override
  public Order findByOrderId(String orderId) {
    return mapper.toDo(
        jpaOrderRepository.findById(orderId).orElseThrow(notFoundException(NOT_FOUND_CUSTOMER)));
  }
}
