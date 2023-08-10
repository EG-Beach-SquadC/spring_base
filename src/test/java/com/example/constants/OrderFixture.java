package com.example.constants;

import com.example.domain.entity.Order;
import com.example.domain.entity.OrderedProduct;
import com.example.infrastructure.persistence.entity.OrderPo;
import com.example.presentation.vo.OrderRequestDto;

import java.math.BigDecimal;
import java.util.List;

import static com.example.domain.entity.OrderStatus.CREATED;
import static java.time.LocalDateTime.ofEpochSecond;
import static java.time.ZoneOffset.UTC;

public class OrderFixture {
  public final static String CUSTOMER_ID = "customer-1";

  public final static OrderPo ORDER_PO1 = new OrderPo("id-1", CUSTOMER_ID, BigDecimal.ONE, null,
      CREATED, ofEpochSecond(1691377675, 0, UTC), ofEpochSecond(1691377675, 0, UTC));
  public final static OrderPo ORDER_PO2 = new OrderPo("id-2", CUSTOMER_ID, BigDecimal.ONE, null,
      CREATED, ofEpochSecond(1691377675, 0, UTC), ofEpochSecond(1691377675, 0, UTC));

  public static final String PRODUCTS_DETAIL =
      "[{\"id\": 1, \"name\": \"test\", \"price\": 1.0, \"amount\": 1}, {\"id\": 2, \"name\": \"test\", \"price\": 1.0, \"amount\": 1}]";
  public final static OrderPo ORDER_PO3 =
      new OrderPo("id-3", CUSTOMER_ID, BigDecimal.ONE, PRODUCTS_DETAIL, CREATED,
          ofEpochSecond(1691377675, 0, UTC), ofEpochSecond(1691377675, 0, UTC));

  public final static Order ORDER1 = new Order("id-1", "customer-1", BigDecimal.ONE, null, CREATED,
      ofEpochSecond(1691377675, 0, UTC), ofEpochSecond(1691377675, 0, UTC));

  public final static Order ORDER2 = new Order("id-2", "customer-1", BigDecimal.ONE, null, CREATED,
      ofEpochSecond(1691377675, 0, UTC), ofEpochSecond(1691377675, 0, UTC));

  public final static OrderRequestDto ORDER_REQUEST_1 = new OrderRequestDto("test",
      List.of(new OrderedProduct("id-1", "customer-1", BigDecimal.ONE, 1)));

  public final static OrderRequestDto ORDER_REQUEST_INVALID = new OrderRequestDto("test",
      List.of(new OrderedProduct("id-3", "customer-1", BigDecimal.ONE, 1)));
}
