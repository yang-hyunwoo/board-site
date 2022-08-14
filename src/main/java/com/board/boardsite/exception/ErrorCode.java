package com.board.boardsite.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATED_EMAIL(HttpStatus.CONFLICT,"User email is duplicated"),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND , "Email not founded"),
    ;

    private HttpStatus status;
    private String message;
}
