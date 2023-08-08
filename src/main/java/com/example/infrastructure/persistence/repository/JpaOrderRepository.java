package com.example.infrastructure.persistence.repository;

import com.example.infrastructure.persistence.entity.OrderPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<OrderPo, String> {

}
