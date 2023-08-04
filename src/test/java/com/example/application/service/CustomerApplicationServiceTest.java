package com.example.application.service;


import com.example.domain.entity.Customer;
import com.example.domain.repository.CustomerRepository;
import com.example.presentation.vo.CustomerDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.powermock.api.mockito.PowerMockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerApplicationServiceTest {

  @InjectMocks
  private CustomerApplicationService customerApplicationService;

  @Mock
  private CustomerRepository customerRepository;

  @Test
  void name() {
    when(customerRepository.findById("1"))
        .thenReturn(new Customer("1", "test", LocalDateTime.now(), LocalDateTime.now()));

    CustomerDto customerDto = customerApplicationService.findById("1");

    Assertions.assertEquals(customerDto.getName(), "test");
  }
}
