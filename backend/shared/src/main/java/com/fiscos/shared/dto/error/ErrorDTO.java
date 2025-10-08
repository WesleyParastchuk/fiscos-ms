package com.fiscos.shared.dto.error;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.time.Instant;

/**
 * Standard error payload shared across microservices.
 * The timestamp relies on {@link Instant}, guaranteeing a UTC reference.
 */
@Value
@Builder
public class ErrorDTO {

    @NonNull
    String message;
    int status;
    @NonNull
    String code;
    @NonNull
    String path;

    @Builder.Default
    Instant timestamp = Instant.now();

    /**
     * Factory method that uses the current UTC instant.
     */
    public static ErrorDTO of(String message, int status, String code, String path) {
        return ErrorDTO.builder()
                .message(message)
                .status(status)
                .code(code)
                .path(path)
                .build();
    }
}
