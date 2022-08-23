package com.board.boardsite.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATED_EMAIL(HttpStatus.CONFLICT,"이미 사용중이거나 탈퇴한 이메일입니다."),
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND , "이메일이 존재 하지 않거나 인증이 완료되지 않은 이메일 입니다."),
    EMAIL_TIME_INVAILED(HttpStatus.UNAUTHORIZED, "Email expired"),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED , "이메일이나 패스워드가 올바르지 않습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND , "User not founded"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED,"Token is invalid"),
    ARTICLE_DELETE_FAIL(HttpStatus.NOT_FOUND , "Article is deleted failed"),
    ARTICLE_UPDATE_FAIL(HttpStatus.NOT_FOUND , "Article is failed"),
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND , "게시글을 찾을 수 없습니다."),

    ARTICLE_COMMENT_UNAUTHORIZED(HttpStatus.UNAUTHORIZED , "Article comment not unauthorized"),

    ARTICLE_COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND , "Article comment not founded"),
    TRAVEL_AGENCY_NOT_FOUND(HttpStatus.NOT_FOUND , "여행사가 존재 하지 않습니다."),

    TRAVEL_AGENCY_LIST_NOT_FOUND(HttpStatus.NOT_FOUND , "여행 목록이 존재 하지 않습니다."),
    TRAVEL_AGENCY_DETAIL_NOT_FOUND(HttpStatus.NOT_FOUND , "TravelAgencyDetail not founded"),

    INTERNAL_SERVER_ERROR2(HttpStatus.INTERNAL_SERVER_ERROR, "잠시 후 다시 이용해 주세요."),

    TRAVEL_PAY_NOT_FOUND(HttpStatus.NOT_FOUND, "결제 내역이 없습니다."),
    ;

    private HttpStatus status;
    private String message;
}
