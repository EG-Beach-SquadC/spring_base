package com.example.presentation.vo;

import com.example.domain.entity.Product;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderRequestDto {

  @NotBlank(message = "Customer ID cannot be null")
  private String customerId;

  @NotEmpty(message = "Products cannot be empty")
  private List<Product> products;
}
