package com.northcoders.gamboge.waddl_api.exception.handler;

import com.northcoders.gamboge.waddl_api.exception.TaskNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({TaskNotFoundException.class})
    private ResponseEntity<Object> handleIncorrectHttpRequestTypeException(TaskNotFoundException e) {
        return this.respondToGenericExceptionMessage(e, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Object> respondToGenericExceptionMessage(RuntimeException e, HttpStatus code) {
        this.logger.error(e.getMessage());

        return ResponseEntity
                .status(code)
                .body(e.getMessage());
    }
}