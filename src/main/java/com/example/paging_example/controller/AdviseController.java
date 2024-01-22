package com.example.paging_example.controller;

import com.example.paging_example.exp.AppBadException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviseController {
    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException appBatException) {
        return ResponseEntity.badRequest().body(appBatException.getMessage());
    }
}
