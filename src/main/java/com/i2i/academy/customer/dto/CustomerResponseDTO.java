package com.i2i.academy.customer.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Customer representation returned by the API")
public class CustomerResponseDTO {

    @Schema(description = "Unique identifier", example = "1")
    private Long id;

    @Schema(description = "Customer's first name", example = "Ada")
    private String firstName;

    @Schema(description = "Customer's last name", example = "Lovelace")
    private String lastName;

    @Schema(description = "Unique email address", example = "ada.lovelace@example.com")
    private String email;

    @Schema(description = "Contact phone number", example = "+90 555 123 45 67")
    private String phone;

    @Schema(description = "City of residence", example = "Istanbul")
    private String city;

    @Schema(description = "Timestamp when the customer was created", example = "2026-07-09T10:15:30")
    private LocalDateTime createdAt;
}
