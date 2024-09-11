package com.example.recime.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class HandleBadRequestConfiguration {

    private static final Logger log = LoggerFactory.getLogger(HandleBadRequestConfiguration.class);

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String,String>> handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        log.error("Missing parameter on request: {}", name);
        return ResponseEntity.badRequest().body(Map.of("message","A " + name + " required for filtering trending recipes"));
    }
}
