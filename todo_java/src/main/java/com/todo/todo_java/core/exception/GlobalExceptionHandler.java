package com.todo.todo_java.core.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorMessageDTO>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ErrorMessageDTO> dto = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach((err) -> {
            String message = messageSource.getMessage(err, LocaleContextHolder.getLocale());
            dto.add(new ErrorMessageDTO(err.getField(), message));
        });

        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

}
