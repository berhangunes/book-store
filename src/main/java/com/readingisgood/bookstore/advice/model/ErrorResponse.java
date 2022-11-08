package com.readingisgood.bookstore.advice.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private final Integer errorCode;
    private final String errorMessage;
    public ErrorResponse(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
