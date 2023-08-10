package com.example.application.assembler;

import com.example.domain.entity.Order;
import com.example.presentation.vo.OrderDto;
import com.example.presentation.vo.OrderRequestDto;
import com.example.presentation.vo.OrderResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDtoMapper {

  OrderDtoMapper mapper = getMapper(OrderDtoMapper.class);

  OrderDto toDto(Order order);

  Order requestDtoToDo(OrderRequestDto orderRequestDto);

  OrderResponseDto doToResponseDto(Order order);
}
