package com.example.infrastructure.persistence.repository;

import com.example.common.base.BaseJpaRepository;
import com.example.infrastructure.persistence.entity.ProductPo;

public interface JpaProductRepository extends BaseJpaRepository<ProductPo, String> {
}
