package com.example.infrastructure.persistence.repository.domain;

import com.example.domain.entity.Product;
import com.example.infrastructure.persistence.assembler.ProductDataMapper;
import com.example.infrastructure.persistence.repository.JpaProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.example.constants.ProductFixture.PRODUCT_PO1;
import static com.example.constants.ProductFixture.PRODUCT_PO2;
import static com.example.constants.ProductFixture.PRODUCT_PO3;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductDomainRepositoryTest {
  @InjectMocks
  private ProductDomainRepository productDomainRepository;
  @Mock
  private JpaProductRepository jpaProductRepository;
  private final ProductDataMapper mapper = ProductDataMapper.MAPPER;

  @Test
  void should_return_all_products_when_products_exist_in_db() {
    when(jpaProductRepository.findAll()).thenReturn(List.of(PRODUCT_PO1, PRODUCT_PO2, PRODUCT_PO3));

    List<Product> products = productDomainRepository.findAll();

    assertAll(() -> assertEquals(3, products.size()),
        () -> assertEquals("id-1", products.get(0).getId()),
        () -> assertEquals("id-2", products.get(1).getId()),
        () -> assertEquals("id-3", products.get(2).getId()));
  }

  @Test
  void should_return_empty_list_when_no_products_in_db() {
    when(jpaProductRepository.findAll()).thenReturn(List.of());

    List<Product> products = productDomainRepository.findAll();

    assertTrue(products.isEmpty());
  }

}
