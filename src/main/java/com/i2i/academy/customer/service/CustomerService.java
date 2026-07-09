package com.i2i.academy.customer.service;

import com.i2i.academy.customer.dto.CustomerRequestDTO;
import com.i2i.academy.customer.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {

    CustomerResponseDTO create(CustomerRequestDTO request);

    CustomerResponseDTO getById(Long id);

    List<CustomerResponseDTO> getAll();

    CustomerResponseDTO update(Long id, CustomerRequestDTO request);

    void delete(Long id);
}
