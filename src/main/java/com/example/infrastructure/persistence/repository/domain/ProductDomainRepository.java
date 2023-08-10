package com.example.infrastructure.persistence.repository.domain;

import com.example.domain.entity.OrderedProduct;
import com.example.domain.entity.Product;
import com.example.domain.entity.ProductStatus;
import com.example.domain.repository.ProductRepository;
import com.example.infrastructure.persistence.assembler.ProductDataMapper;
import com.example.infrastructure.persistence.entity.ProductPo;
import com.example.infrastructure.persistence.repository.JpaProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class ProductDomainRepository implements ProductRepository {
  private final JpaProductRepository jpaProductRepository;
  private final ProductDataMapper mapper = ProductDataMapper.MAPPER;

  public BigDecimal countTotal(List<OrderedProduct> products) {
    Map<String, Integer> productQuantities = products.stream()
        .collect(Collectors.toMap(OrderedProduct::getId, OrderedProduct::getAmount));

    Set<String> productIds = productQuantities.keySet();

    Map<String, ProductPo> productMap = jpaProductRepository.findAllById(productIds).stream()
        .collect(Collectors.toMap(ProductPo::getId, p -> p));

    if (productMap.size() != productQuantities.size()) {
      throw new RuntimeException();
    }

    if (productMap.entrySet().stream()
        .anyMatch(p -> p.getValue().getStatus().equals(ProductStatus.INVALID)
            || p.getValue().getPrice() == null))
      throw new RuntimeException();

    return productQuantities.entrySet().stream().map(entry -> {
      String productId = entry.getKey();
      int quantity = entry.getValue();
      BigDecimal price = productMap.get(productId).getPrice();
      return price.multiply(BigDecimal.valueOf(quantity));
    }).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  @Override
  public List<Product> findAll() {
    List<ProductPo> productPos = jpaProductRepository.findAll();
    return productPos.stream().map(mapper::toProduct).collect(Collectors.toList());
  }
}
