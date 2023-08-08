package com.example.infrastructure.persistence.repository;

import com.example.infrastructure.persistence.entity.CustomerPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCustomerRepository extends JpaRepository<CustomerPo, String> {
}
