package com.example.presentation.vo;

import com.example.domain.entity.OrderStatus;
import com.example.domain.entity.OrderedProduct;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
  private String id;
  private Double total;
  private List<OrderedProduct> products;
  private OrderStatus status;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
