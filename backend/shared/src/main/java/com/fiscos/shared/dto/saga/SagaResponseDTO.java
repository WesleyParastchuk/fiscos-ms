package com.fiscos.shared.dto.saga;

import lombok.Builder;
import lombok.Value;

import com.fiscos.shared.dto.error.ErrorDTO;

import java.util.Objects;

/**
 * Generic response wrapper leveraged across saga orchestrations.
 * Encapsulates success flag, an {@link ErrorDTO} and a payload of type
 * {@code T}.
 */
@Value
@Builder
public class SagaResponseDTO<T> {

    boolean success;
    ErrorDTO error;
    T data;

    /**
     * Creates a successful response with the provided payload and no error.
     */
    public static <T> SagaResponseDTO<T> success(T data) {
        return SagaResponseDTO.<T>builder()
                .success(true)
                .data(data)
                .build();
    }

    /**
     * Creates an error response with the provided {@link ErrorDTO} and no data.
     */
    public static <T> SagaResponseDTO<T> error(ErrorDTO error) {
        return SagaResponseDTO.<T>builder()
                .success(false)
                .error(Objects.requireNonNull(error, "error must not be null"))
                .build();
    }
}
