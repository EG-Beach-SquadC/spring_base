package com.example.infrastructure.persistence.assembler;

import com.example.domain.entity.Order;
import com.example.domain.entity.Product;
import com.example.infrastructure.persistence.entity.OrderPo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.io.IOException;
import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDataMapper {
    OrderDataMapper mapper = getMapper(OrderDataMapper.class);

    @Mapping(target = "products", source = "products")
    Order toDo(OrderPo orderPo);

    default List<Product> mapProducts(String products) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(products, new TypeReference<List<Product>>() {
            });
        } catch (IOException e) {
            return null;
        }
    }
}
