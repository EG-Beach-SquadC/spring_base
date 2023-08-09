package com.example.constants;

import com.example.domain.entity.Order;
import com.example.domain.entity.Product;
import com.example.domain.entity.ProductStatus;
import com.example.infrastructure.persistence.entity.ProductPo;

import java.math.BigDecimal;

import static com.example.domain.entity.OrderStatus.CREATED;
import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.ofEpochSecond;
import static java.time.ZoneOffset.UTC;

public class ProductFixture {
  public final static String CUSTOMER_ID = "customer-1";

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
  public final static ProductPo PRODUCT_PO3 =
      new ProductPo("id-3", "product-3", BigDecimal.ONE, ProductStatus.INVALID, now(), now());


  public final static Order ORDER1 = new Order("id-1", "customer-1", BigDecimal.ONE, null, CREATED,
      ofEpochSecond(1691377675, 0, UTC), ofEpochSecond(1691377675, 0, UTC));

  public final static Order ORDER2 = new Order("id-2", "customer-1", BigDecimal.ONE, null, CREATED,
      ofEpochSecond(1691377675, 0, UTC), ofEpochSecond(1691377675, 0, UTC));

}
