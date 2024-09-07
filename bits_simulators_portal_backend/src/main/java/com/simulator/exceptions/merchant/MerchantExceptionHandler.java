package com.simulator.exceptions.merchant;

import com.simulator.exceptions.ErrorObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class MerchantExceptionHandler {
    private static final Logger logger = LogManager.getLogger(MerchantExceptionHandler.class);
    @ExceptionHandler(MerchantException.class)
    public ResponseEntity<ErrorObject> handleMerchantException(MerchantException ex, WebRequest webRequest) {
        logger.info("im here ........................");
        ErrorObject errorObject = new ErrorObject(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                new Date()
        );
        return new ResponseEntity<>(
                errorObject,
                HttpStatus.NOT_FOUND
        );
    }
}