package com.example.domain.repository;

import com.example.domain.entity.Order;
import com.example.infrastructure.persistence.entity.OrderPo;

public interface OrderRepository {
    Order createOrder(OrderPo orderPo);
}
