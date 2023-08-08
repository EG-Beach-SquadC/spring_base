package com.example.presentation.vo;

import com.example.domain.entity.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
  private String id;
  private String name;
  private BigDecimal price;
  private ProductStatus status;
}
