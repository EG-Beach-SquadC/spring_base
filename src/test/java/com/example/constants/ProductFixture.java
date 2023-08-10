package com.example.constants;

import com.example.domain.entity.OrderedProduct;
import com.example.domain.entity.Product;
import com.example.domain.entity.ProductStatus;
import com.example.infrastructure.persistence.entity.ProductPo;

import java.math.BigDecimal;

import static java.time.LocalDateTime.now;

public class ProductFixture {
  public final static Product PRODUCT1 =
      new Product("id-1", "product-1", BigDecimal.ONE, ProductStatus.VALID);
  public final static Product PRODUCT2 =
      new Product("id-2", "product-2", BigDecimal.ONE, ProductStatus.VALID);
  public final static Product PRODUCT3 =
      new Product("id-3", "product-3", BigDecimal.ONE, ProductStatus.INVALID);

  public final static ProductPo PRODUCT_PO1 =
      new ProductPo("id-1", "product-1", BigDecimal.ONE, ProductStatus.VALID, now(), now());
  public final static ProductPo PRODUCT_PO2 =
      new ProductPo("id-2", "product-2", BigDecimal.ONE, ProductStatus.VALID, now(), now());
  public final static ProductPo PRODUCT_PO_INVALID =
      new ProductPo("id-3", "product-3", BigDecimal.ONE, ProductStatus.INVALID, now(), now());

  public final static ProductPo PRODUCT_PO_WITHOUT_PRICE =
      new ProductPo("id-2", "product-3", null, ProductStatus.VALID, now(), now());

  public final static OrderedProduct ORDERED_PRODUCT_1 =
      new OrderedProduct("id-1", "product-1", null, 1);

  public final static OrderedProduct ORDERED_PRODUCT_WITH_INVALID_ID =
      new OrderedProduct("invalid_id", "product-1", null, 1);

  public final static OrderedProduct ORDERED_PRODUCT_2 =
      new OrderedProduct("id-2", "product-2", null, 1);
  public final static OrderedProduct ORDERED_PRODUCT_INVALID =
      new OrderedProduct("id-3", "product-3", null, 1);
}
