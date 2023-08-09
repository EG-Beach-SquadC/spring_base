package com.example.presentation.vo;

import com.example.domain.entity.OrderStatus;
import com.example.domain.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
  private List<Product> products;
  private OrderStatus status;
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
