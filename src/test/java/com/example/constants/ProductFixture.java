package com.example.constants;

import com.example.domain.entity.Product;
import com.example.domain.entity.ProductStatus;

import java.math.BigDecimal;

public class ProductFixture {
  public final static Product PRODUCT1 =
      new Product("id-1", "product-1", BigDecimal.ONE, ProductStatus.VALID);
  public final static Product PRODUCT2 =
      new Product("id-2", "product-2", BigDecimal.ONE, ProductStatus.VALID);
  public final static Product PRODUCT3 =
      new Product("id-3", "product-3", BigDecimal.ONE, ProductStatus.INVALID);

}
