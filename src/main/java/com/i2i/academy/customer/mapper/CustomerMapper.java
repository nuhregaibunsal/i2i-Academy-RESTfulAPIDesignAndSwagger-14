package com.i2i.academy.customer.mapper;

import com.i2i.academy.customer.dto.CustomerRequestDTO;
import com.i2i.academy.customer.dto.CustomerResponseDTO;
import com.i2i.academy.customer.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequestDTO dto) {
        return Customer.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .city(dto.getCity())
                .build();
    }

    public void updateEntity(Customer entity, CustomerRequestDTO dto) {
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setCity(dto.getCity());
    }

    public CustomerResponseDTO toResponse(Customer entity) {
        return CustomerResponseDTO.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .city(entity.getCity())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
