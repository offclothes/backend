package com.app.oc.exception.advice;


import com.app.oc.exception.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        Map<String, Object> body = new LinkedHashMap<>();
        Map<String, String> errors = new HashMap<>();
        body.put("status", status.value());

        //필드 오류
        allErrors.stream().map(error -> {
            //필드명
            String fieldName = ((FieldError) error).getField();
            //error메시지
            String errorMessage = getmessage(error);

            errors.put(fieldName, errorMessage);
            return errors;
        }).collect(Collectors.toList());

        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

    private String getmessage(ObjectError error) {
        String errorMessage
                = Arrays.stream(Objects.requireNonNull(error.getCodes()))
                .map(c -> {
                    try {
                        Object[] argumemt = error.getArguments();
                        log.info("c = {}", c);
                        return getMessageSource().getMessage(c, argumemt, null);
                    } catch (NoSuchMessageException noSuchMessageException) {
                        return null;
                    }
                }).filter(obj -> Objects.nonNull(obj))
                .findFirst()
                .orElse(error.getDefaultMessage());
        return errorMessage;
    }


    //400
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    @ExceptionHandler(value = {IllegalArgumentException.class,IllegalStateException.class})
    public ErrorResult illegalExHandler(RuntimeException e) {
        log.info("who");
        log.error("[execeptionHandler] ex", e);
        return new ErrorResult(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }


    //서버오류
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "내부오류");
    }



    //Bean
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.value());

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        body.put("errors", errors);
//
        log.info("Errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }


}
