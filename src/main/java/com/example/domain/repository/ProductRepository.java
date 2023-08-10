package com.example.domain.repository;

import com.example.domain.entity.Product;
import com.example.infrastructure.persistence.entity.ProductPo;

import java.util.List;

public interface ProductRepository {
  List<Product> findAll();

  List<ProductPo> findProductByID(Iterable<String> strings);
}
