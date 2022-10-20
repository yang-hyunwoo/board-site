package com.board.boardsite.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    DUPLICATED_EMAIL(HttpStatus.CONFLICT,"이미 사용중이거나 탈퇴한 이메일입니다."),

    DUPLICATED_NICKNAME(HttpStatus.CONFLICT,"이미 사용중인 닉네임 입니다."),

    DUPLICATED_NAME(HttpStatus.CONFLICT,"이미 사용중인 여행사 이름 입니다."),
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

    ARTICLE_UNAUTHORIZED(HttpStatus.UNAUTHORIZED , "접근 권한이 없습니다."),

    TRAVEL_AGENCY_LIST_NOT_FOUND(HttpStatus.NOT_FOUND , "여행 목록이 존재 하지 않습니다."),
    TRAVEL_AGENCY_DETAIL_NOT_FOUND(HttpStatus.NOT_FOUND , "여행 목록이 없습니다."),

    INTERNAL_SERVER_ERROR2(HttpStatus.INTERNAL_SERVER_ERROR, "잠시 후 다시 이용해 주세요."),


    TRAVEL_AGENCY_LIST_DATE(HttpStatus.NOT_FOUND , "금일 여행 일정이 아닙니다."),

    TRAVEL_PAY_NOT_FOUND(HttpStatus.NOT_FOUND, "결제 내역이 없습니다."),

    REFUND_FAIL(HttpStatus.NOT_FOUND, "환불 중 오류가 발생했습니다."),

    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "첨부파일을 찾을수 없습니다."),

    CHAT_ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "채팅방을 찾을수 없습니다."),

    CHAT_ROOM_NOT_PERMISSION(HttpStatus.UNAUTHORIZED, "권한이 없는 채팅방입니다."),

    CHAT_ROOM_FULL_COUNT(HttpStatus.NOT_FOUND, "인원이 꽉찬 채팅방입니다."),

    TOUR_NOT_FOUND(HttpStatus.NOT_FOUND , "관광지를 찾을 수 없습니다."),

    LIKE_NOT(HttpStatus.CONFLICT,"좋아요 에러"),

    NOT_PERMITTION(HttpStatus.CONFLICT,"권한이 없습니다."),

    NOT_ALLOWED(HttpStatus.CONFLICT,"이메일 인증 및 승인이 완료 되지 않았습니다."),

    NOT_DELETE_TRAVEL_AGENCY(HttpStatus.UNAUTHORIZED,"사용 중인 여행 리스트가 있습니다."),

    NOT_TRAVEL_AGENCY_RESERVATION(HttpStatus.NOT_FOUND,"존재하지 않은 예약 입니다."),
    ;

    private HttpStatus status;
    private String message;
}
