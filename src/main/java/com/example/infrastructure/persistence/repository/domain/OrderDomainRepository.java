package com.example.infrastructure.persistence.repository.domain;

import com.example.infrastructure.persistence.assembler.OrderDataMapper;
import com.example.infrastructure.persistence.repository.JpaOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderDomainRepository {
    private final JpaOrderRepository jpaOrderRepository;
    private final OrderDataMapper mapper = OrderDataMapper.mapper;

}
