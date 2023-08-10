package com.example.application.service;

import com.example.application.assembler.OrderDtoMapper;
import com.example.domain.entity.Order;
import com.example.domain.entity.OrderStatus;
import com.example.domain.entity.OrderedProduct;
import com.example.domain.entity.ProductStatus;
import com.example.domain.repository.OrderRepository;
import com.example.domain.repository.ProductRepository;
import com.example.infrastructure.persistence.assembler.OrderDataMapper;
import com.example.infrastructure.persistence.entity.ProductPo;
import com.example.presentation.vo.OrderDto;
import com.example.presentation.vo.OrderRequestDto;
import com.example.presentation.vo.OrderResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderApplicationService {

  private final OrderRepository orderRepository;

  private final ProductRepository productRepository;

  private final OrderDtoMapper orderDtoMapper = OrderDtoMapper.mapper;

  private final OrderDataMapper orderDataMapper = OrderDataMapper.mapper;

  public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
    Order order = orderDtoMapper.requestDtoToDo(orderRequestDto);
    Map<String, Integer> productQuantities = order.getProducts().stream()
        .collect(Collectors.toMap(OrderedProduct::getId, OrderedProduct::getAmount));

    Map<String, ProductPo> productMap =
        productRepository.findProductByID(productQuantities.keySet()).stream()
            .collect(Collectors.toMap(ProductPo::getId, p -> p));

    if (!checkValidation(productQuantities, productMap))
      throw new RuntimeException();

    BigDecimal total = calculateTotal(productQuantities, productMap);
    order.setTotal(total);
    order.setStatus(OrderStatus.CREATED);
    return orderDtoMapper.doToResponseDto(orderRepository.save(orderDataMapper.toPo(order)));
  }

  private boolean checkValidation(Map<String, Integer> productQuantities,
      Map<String, ProductPo> productMap) {
    return productMap.size() == productQuantities.size() && productMap.entrySet().stream()
        .noneMatch(p -> p.getValue().getStatus().equals(ProductStatus.INVALID)
            && p.getValue().getPrice() != null);
  }

  private BigDecimal calculateTotal(Map<String, Integer> productQuantities,
      Map<String, ProductPo> productMap) {
    return productQuantities.entrySet().stream().map(entry -> {
      String productId = entry.getKey();
      int quantity = entry.getValue();
      BigDecimal price = productMap.get(productId).getPrice();
      return price.multiply(BigDecimal.valueOf(quantity));
    }).reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public List<OrderDto> retrieveOrders(String customerId) {
    return orderRepository.findAllByCustomerId(customerId).stream().map(orderDtoMapper::toDto)
        .collect(Collectors.toList());
  }

  public OrderDto retrieveOrder(String orderId) {
    return orderDtoMapper.toDto(orderRepository.findByOrderId(orderId));
  }
}
