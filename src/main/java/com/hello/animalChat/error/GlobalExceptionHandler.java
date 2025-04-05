package com.hello.animalChat.error;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    //데이터 접근 관련 에러
    @ExceptionHandler({DataAccessException.class , GenericJDBCException.class , JDBCConnectionException.class})
    public ResponseEntity<ErrorResult> handleDataAccessException(DataAccessException e) {
        log.error("DB 오류 발생", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResult(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "데이터베이스 오류가 발생했습니다. 잠시 후 다시 시도해주세요."
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> handleException(DataAccessException e) {
        log.error("에러 발생", e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResult(
                        HttpStatus.BAD_REQUEST.value(),
                        "에러 발생"
                ));
    }
}

