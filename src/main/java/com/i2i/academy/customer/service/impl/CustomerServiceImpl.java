package com.i2i.academy.customer.service.impl;

import com.i2i.academy.customer.dto.CustomerRequestDTO;
import com.i2i.academy.customer.dto.CustomerResponseDTO;
import com.i2i.academy.customer.entity.Customer;
import com.i2i.academy.customer.exception.DuplicateResourceException;
import com.i2i.academy.customer.exception.ResourceNotFoundException;
import com.i2i.academy.customer.mapper.CustomerMapper;
import com.i2i.academy.customer.repository.CustomerRepository;
import com.i2i.academy.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    @Transactional
    public CustomerResponseDTO create(CustomerRequestDTO request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("A customer already exists with email: " + request.getEmail());
        }
        Customer customer = customerMapper.toEntity(request);
        customer.setCreatedAt(LocalDateTime.now());
        Customer saved = customerRepository.save(customer);
        return customerMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerResponseDTO getById(Long id) {
        Customer customer = findOrThrow(id);
        return customerMapper.toResponse(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getAll() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public CustomerResponseDTO update(Long id, CustomerRequestDTO request) {
        Customer customer = findOrThrow(id);
        if (!customer.getEmail().equals(request.getEmail())
                && customerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("A customer already exists with email: " + request.getEmail());
        }
        customerMapper.updateEntity(customer, request);
        Customer updated = customerRepository.save(customer);
        return customerMapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Customer customer = findOrThrow(id);
        customerRepository.delete(customer);
    }

    private Customer findOrThrow(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }
}
