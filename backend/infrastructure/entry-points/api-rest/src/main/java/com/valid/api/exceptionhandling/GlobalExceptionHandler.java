package com.valid.api.exceptionhandling;

import com.valid.api.model.response.ErrorResponse;
import com.valid.exception.UserAlreadyExistsException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleCatalogNotFound(UserAlreadyExistsException ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        ErrorResponse error = new ErrorResponse(ex.getMessage(), ex.getCode());
        return handleExceptionInternal(ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }

}
