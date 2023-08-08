package com.example.infrastructure.persistence.repository;

import com.example.infrastructure.persistence.entity.ProductPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductPo, String> {
}
