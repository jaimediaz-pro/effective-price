package com.test.inditex.infrastructure.exceptions.handlers;

import com.test.inditex.infrastructure.exceptions.EffectivePriceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class InditexExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EffectivePriceNotFoundException.class)
    ErrorResponse handleEffectivePriceNotFoundException(EffectivePriceNotFoundException ex) {
        return ErrorResponse.builder(ex, HttpStatus.NOT_FOUND, ex.getMessage()).title("Effective price not found").build();
    }
}
