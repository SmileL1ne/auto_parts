package kz.shop.auto_parts.exceptionHandlers;

import kz.shop.auto_parts.entities.dto.ExceptionResponse;
import kz.shop.auto_parts.exceptions.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Log4j2
public class CustomExceptionHandler {

    @ExceptionHandler({ProductNotFoundException.class, OrderNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex) {
        ExceptionResponse response = generateException(ex.getMessage(),
                HttpStatus.NOT_FOUND.toString(), LocalDateTime.now().toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInputTypeException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidInputTypeException(InvalidInputTypeException ex) {
        ExceptionResponse response = generateException(ex.getMessage(),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now().toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private ExceptionResponse generateException(String message, String code, String date) {
        return new ExceptionResponse(message, code, date);
    }
}
