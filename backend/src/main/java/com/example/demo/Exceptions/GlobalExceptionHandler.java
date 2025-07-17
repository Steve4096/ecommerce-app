package com.example.demo.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler{
    private ResponseEntity<ApiErrorResponseClass> buildErrorResponse(Exception exception, HttpStatus status, HttpServletRequest servletRequest){
        ApiErrorResponseClass responseClass= ApiErrorResponseClass.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .message(exception.getMessage())
                .error(status.getReasonPhrase())
                .path(servletRequest.getRequestURI())
                .build();
        return ResponseEntity.status(status).body(responseClass);
    }

    /*@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException exception){
        String message="Duplicate value detected"+exception.getMostSpecificCause().getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> customerNotFoundException(CustomerNotFoundException exception){
        String message="Customer not found "+exception.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }*/

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponseClass> handleNotFoundException(NotFoundException e,HttpServletRequest request){
        return buildErrorResponse(e,HttpStatus.NOT_FOUND,request);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ApiErrorResponseClass> handleCustomerNotFoundException(CustomerNotFoundException exception,HttpServletRequest request){
        return buildErrorResponse(exception,HttpStatus.NOT_FOUND,request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponseClass> handleDuplicateError(DataIntegrityViolationException exception,HttpServletRequest request){
        return buildErrorResponse(exception,HttpStatus.CONFLICT,request);
    }

//    @ExceptionHandler(DuplicateException.class)
//    public ResponseEntity<ApiErrorResponseClass>
}
