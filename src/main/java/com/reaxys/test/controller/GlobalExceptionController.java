package com.reaxys.test.controller;

import com.reaxys.test.service.Implimentations.FactsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;


/**
 * This class handles all exceptions to make sure that in case of error server will not stop
 * and client will receive response with error code.
 */
@ControllerAdvice
public class GlobalExceptionController {

    private final static Logger logger = LoggerFactory.getLogger(FactsServiceImpl.class);

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> jsonException(HttpMessageNotReadableException ex) {
        logger.error("HttpMessageNotReadableException was thrown");
        return new ResponseEntity<>(ex.getCause().getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<String> fileNotFound(FileNotFoundException ex) {
        logger.error("FileNotFoundException was thrown");
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}