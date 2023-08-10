package com.example.domain.repository;

import com.example.domain.entity.OrderedProduct;
import com.example.domain.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository {
  List<Product> findAll();

  BigDecimal countTotal(List<OrderedProduct> products);
}
