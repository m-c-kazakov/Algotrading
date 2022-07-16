package com.finance.strategyGeneration.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@ControllerAdvice
public class ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception ex, WebRequest request) {
        log.error("Ошибка в модуле генерации  стратегий: {}", ex.getMessage(), ex);
        return new ResponseEntity<>(
                String.valueOf(ex.getCause()), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}
