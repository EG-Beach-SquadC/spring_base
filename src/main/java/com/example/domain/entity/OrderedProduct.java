package com.example.domain.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderedProduct {
  private String id;
  private String name;
  private BigDecimal price;
  private Integer amount;
}
