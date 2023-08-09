package com.example.application.assembler;

import com.example.domain.entity.Order;
import com.example.presentation.vo.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDtoMapper {

  OrderDtoMapper MAPPER = getMapper(OrderDtoMapper.class);

  OrderDto toDto(Order order);
}
