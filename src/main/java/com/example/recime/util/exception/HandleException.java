package com.example.recime.util.exception;

import com.example.recime.model.rest.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class HandleException {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseError> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        log.error("Missing parameter on request: {}", name);
        return ResponseEntity.badRequest().body(ResponseError.builder().message("A " + name + " required for filtering trending recipes").build());
    }

}
