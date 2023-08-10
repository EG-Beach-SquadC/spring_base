package com.example.infrastructure.persistence.repository.domain;

import com.example.domain.entity.OrderedProduct;
import com.example.domain.entity.Product;
import com.example.infrastructure.persistence.assembler.ProductDataMapper;
import com.example.infrastructure.persistence.repository.JpaProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static com.example.constants.ProductFixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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
    when(jpaProductRepository.findAll())
        .thenReturn(List.of(PRODUCT_PO1, PRODUCT_PO2, PRODUCT_PO_INVALID));

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

  @Test
  void should_count_total_when_give_a_product_list() {
    List<OrderedProduct> orderedProducts = List.of(ORDERED_PRODUCT_1, ORDERED_PRODUCT_2);
    when(jpaProductRepository.findAllById(any())).thenReturn(List.of(PRODUCT_PO1, PRODUCT_PO2));

    BigDecimal total = productDomainRepository.countTotal(orderedProducts);

    assertEquals(2.00, total.doubleValue());
  }

  @Test
  void should_throw_exception_when_give_a_product_list_contain_invalid_one() {
    List<OrderedProduct> orderedProducts = List.of(ORDERED_PRODUCT_1, ORDERED_PRODUCT_INVALID);
    when(jpaProductRepository.findAllById(any()))
        .thenReturn(List.of(PRODUCT_PO1, PRODUCT_PO_INVALID));

    assertThrows(RuntimeException.class, () -> productDomainRepository.countTotal(orderedProducts));
  }

  @Test
  void should_throw_exception_when_give_a_product_list_contain_invalid_product_id() {
    List<OrderedProduct> orderedProducts =
        List.of(ORDERED_PRODUCT_WITH_INVALID_ID, ORDERED_PRODUCT_2);
    when(jpaProductRepository.findAllById(any())).thenReturn(List.of(PRODUCT_PO2));

    assertThrows(RuntimeException.class, () -> productDomainRepository.countTotal(orderedProducts));
  }

  @Test
  void should_throw_exception_when_give_a_product_list_contain_no_price_product() {
    List<OrderedProduct> orderedProducts = List.of(ORDERED_PRODUCT_1, ORDERED_PRODUCT_2);
    when(jpaProductRepository.findAllById(any()))
        .thenReturn(List.of(PRODUCT_PO1, PRODUCT_PO_WITHOUT_PRICE));

    assertThrows(RuntimeException.class, () -> productDomainRepository.countTotal(orderedProducts));
  }

}
