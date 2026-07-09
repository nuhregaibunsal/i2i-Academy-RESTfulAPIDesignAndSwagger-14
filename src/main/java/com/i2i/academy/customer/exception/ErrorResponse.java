package com.i2i.academy.customer.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@Schema(description = "Standard error response")
public class ErrorResponse {

    @Schema(description = "Time the error occurred", example = "2026-07-09T10:15:30")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP status code", example = "404")
    private int status;

    @Schema(description = "Short error description", example = "Not Found")
    private String error;

    @Schema(description = "Human-readable message", example = "Customer not found with id: 5")
    private String message;

    @Schema(description = "Field-level validation errors, when applicable")
    private Map<String, String> validationErrors;
}
