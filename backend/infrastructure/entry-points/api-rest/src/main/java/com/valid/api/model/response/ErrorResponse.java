package com.valid.api.model.response;

import com.valid.enums.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    String message;
    ErrorCode code;
}
