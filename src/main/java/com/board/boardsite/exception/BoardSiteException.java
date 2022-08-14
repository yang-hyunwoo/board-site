package com.board.boardsite.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BoardSiteException extends RuntimeException{

    private ErrorCode errorCode;

    private String message;

    public BoardSiteException(ErrorCode errorCode){
        this.errorCode =errorCode;
        this.message =null;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        }
        return String.format("%s. %s" , errorCode.getMessage() , message);
    }
}
