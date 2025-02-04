package com.example.domain.repository;

import com.example.domain.entity.Order;
import com.example.infrastructure.persistence.entity.OrderPo;

import java.util.List;

public interface OrderRepository {
  Order save(OrderPo orderPo);

  List<Order> findAllByCustomerId(String customerId);

  Order findByOrderId(String orderId);
}
