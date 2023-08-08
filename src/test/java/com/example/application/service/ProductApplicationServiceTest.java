package com.example.application.service;

import com.example.application.assembler.ProductDtoMapper;
import com.example.domain.repository.ProductRepository;
import com.example.presentation.vo.ProductDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.constants.ProductFixture.PRODUCT1;
import static com.example.constants.ProductFixture.PRODUCT2;
import static com.example.constants.ProductFixture.PRODUCT3;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductApplicationServiceTest {
  private final ProductDtoMapper mapper = ProductDtoMapper.MAPPER;
  @InjectMocks
  private ProductApplicationService productApplicationService;
  @Mock
  private ProductRepository productRepository;

  @Test
  void should_return_all_products_when_products_exist_in_repo() {
    when(productRepository.findAll()).thenReturn(List.of(PRODUCT1, PRODUCT2, PRODUCT3));

    List<ProductDto> productDtos = productApplicationService.findAll();

    assertAll(() -> assertEquals(3, productDtos.size()),
        () -> assertEquals("id-1", productDtos.get(0).getId()),
        () -> assertEquals("id-2", productDtos.get(1).getId()),
        () -> assertEquals("id-3", productDtos.get(2).getId()));
  }


}
