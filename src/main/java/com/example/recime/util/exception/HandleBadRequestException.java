package com.example.recime.util.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
@Slf4j
public class HandleBadRequestException {

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String,String>> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        log.error("Missing parameter on request: {}", name);
        return ResponseEntity.badRequest().body(Map.of("message","A " + name + " required for filtering trending recipes"));
    }
}
