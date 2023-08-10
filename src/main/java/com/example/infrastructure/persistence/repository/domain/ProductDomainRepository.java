package com.example.infrastructure.persistence.repository.domain;

import com.example.domain.entity.Product;
import com.example.domain.repository.ProductRepository;
import com.example.infrastructure.persistence.assembler.ProductDataMapper;
import com.example.infrastructure.persistence.entity.ProductPo;
import com.example.infrastructure.persistence.repository.JpaProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ProductDomainRepository implements ProductRepository {
  private final JpaProductRepository jpaProductRepository;
  private final ProductDataMapper mapper = ProductDataMapper.MAPPER;

  public List<ProductPo> findProductByID(Iterable<String> productIds) {
    return jpaProductRepository.findAllById(productIds);
  }

  @Override
  public List<Product> findAll() {
    List<ProductPo> productPos = jpaProductRepository.findAll();
    return productPos.stream().map(mapper::toProduct).collect(Collectors.toList());
  }
}
