package com.fiscos.shared.dto.error;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

class ErrorDTOTest {

    @Test
    void factoryShouldPopulateFieldsUsingCurrentInstant() {
        ErrorDTO dto = ErrorDTO.of("Not found", 404, "NF-404", "/api/resource");

        assertThat(dto.getMessage()).isEqualTo("Not found");
        assertThat(dto.getStatus()).isEqualTo(404);
        assertThat(dto.getCode()).isEqualTo("NF-404");
        assertThat(dto.getPath()).isEqualTo("/api/resource");
        assertThat(dto.getTimestamp()).isNotNull();
    }

    @Test
    void constructorShouldRespectProvidedTimestamp() {
        Instant fixedInstant = Instant.parse("2025-10-07T15:00:00Z");

        ErrorDTO dto = ErrorDTO.builder()
                .message("Bad request")
                .status(400)
                .code("BR-400")
                .path("/api/resource")
                .timestamp(fixedInstant)
                .build();

        assertThat(dto.getTimestamp()).isEqualTo(fixedInstant);
    }
}
