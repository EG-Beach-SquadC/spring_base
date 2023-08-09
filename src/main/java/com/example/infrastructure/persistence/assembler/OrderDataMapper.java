package com.example.infrastructure.persistence.assembler;

import com.example.domain.entity.Order;
import com.example.domain.entity.Product;
import com.example.infrastructure.persistence.entity.OrderPo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDataMapper {
  OrderDataMapper mapper = getMapper(OrderDataMapper.class);
  ObjectMapper objectMapper = new ObjectMapper();

  @Mapping(target = "products", expression = "java(toProducts(orderPo))")
  Order toDo(OrderPo orderPo);

  default List<Product> toProducts(OrderPo orderPo) {
    try {
      return objectMapper.readValue(orderPo.getProducts(), new TypeReference<>() {});
    } catch (Exception e) {
      return null;
    }
  }
}
