package com.example.application.assembler;

import com.example.domain.entity.Order;
import com.example.domain.entity.Product;
import com.example.infrastructure.persistence.entity.OrderPo;
import com.example.presentation.vo.OrderRequestDto;
import com.example.presentation.vo.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDtoMapper {

    OrderDtoMapper MAPPER = getMapper(OrderDtoMapper.class);

    @Mapping(target = "products", source = "products")
    OrderPo toPo(OrderRequestDto orderRequestDto);

    OrderResponseDto toDto(Order order);

    List<Product> mapProducts(List<Product> products);
}
