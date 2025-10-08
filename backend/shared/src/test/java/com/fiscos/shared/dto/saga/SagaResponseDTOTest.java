package com.fiscos.shared.dto.saga;

import org.junit.jupiter.api.Test;

import com.fiscos.shared.dto.error.ErrorDTO;

import static org.assertj.core.api.Assertions.assertThat;

class SagaResponseDTOTest {

    @Test
    void successFactoryShouldPopulateDataAndSuccessFlag() {
        SagaResponseDTO<String> response = SagaResponseDTO.success("payload");

        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData()).isEqualTo("payload");
        assertThat(response.getError()).isNull();
    }

    @Test
    void errorFactoryShouldPopulateErrorAndDisableSuccessFlag() {
        ErrorDTO error = ErrorDTO.of("Failure", 500, "SAGA-500", "/saga");

        SagaResponseDTO<Void> response = SagaResponseDTO.error(error);

        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getError()).isEqualTo(error);
        assertThat(response.getData()).isNull();
    }
}
