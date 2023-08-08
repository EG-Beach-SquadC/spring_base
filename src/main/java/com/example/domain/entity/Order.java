package com.example.domain.entity;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
  private String id;

  private String customerId;

  private Double total;

  private List<Product> products;

  private OrderStatus status;

  private LocalDateTime createTime;

  private LocalDateTime updateTime;
}
