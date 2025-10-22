package com.fiscos.user.adapter.primary.http.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatusCode status;
    private String error;
    private String message;
    private String path;
    private Instant timestamp;

    public ErrorResponse(HttpStatusCode status, String error, String message, String path) {
        this.timestamp = Instant.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public int getStatusCode() {
        return this.status.value();
    }
}
