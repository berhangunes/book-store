package com.readingisgood.bookstore.advice;

import com.readingisgood.bookstore.advice.exceptions.BookStoreException;
import com.readingisgood.bookstore.advice.model.ErrorResponse;
import com.readingisgood.bookstore.persistence.entity.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> firstHandle(MethodArgumentNotValidException e){
        Map<String,String> errorMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                errorMap.put(error.getField(),error.getDefaultMessage()));
        return errorMap;
    }
    @ExceptionHandler(BookStoreException.class)
    public ResponseEntity<ErrorResponse> secondHandle(BookStoreException e){
       return new ResponseEntity<ErrorResponse>(ErrorResponse.builder()
               .errorCode(e.getCode()).
               errorMessage(e.getMessage()).
               build(),HttpStatus.BAD_REQUEST);
    }
}
