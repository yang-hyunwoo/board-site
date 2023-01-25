package com.board.boardsite.exception;


import com.board.boardsite.dto.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {

    //커스텀 excepitonHandler
    @ExceptionHandler(BoardSiteException.class)
    public ResponseEntity<?> applicationHandler(BoardSiteException e) {
        return ResponseEntity.status(e.getErrorCode().getStatus())
                .body(Response.error(e.getErrorCode().name(),e.getErrorCode().getMessage()));
    }

    //Runtime excepitonHandler
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> applicationHandler(RuntimeException e){
        log.error("Error occurs {}" , e.toString());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Response.error(ErrorCode.INTERNAL_SERVER_ERROR.name(),ErrorCode.INTERNAL_SERVER_ERROR.getMessage()));
    }

    //domain 관련 validation excepitonHandler
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> ConstraintHandler(ConstraintViolationException e) {
        log.error("Error occurs Constraint {}" ,e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()).get(0));
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Response.error("Filed Error", e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()).get(0)));
    }
}
