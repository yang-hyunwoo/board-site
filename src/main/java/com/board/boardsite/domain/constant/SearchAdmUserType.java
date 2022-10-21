package com.board.boardsite.domain.constant;

import lombok.Getter;

public enum SearchAdmUserType {

    NAME("이름"),
    NICKNAME("닉네임"),
    EMAIL("이메일");



    @Getter
    private final String description;

    SearchAdmUserType(String description){
        this.description = description;
    }
}
