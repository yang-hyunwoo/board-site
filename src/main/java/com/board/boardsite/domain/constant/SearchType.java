package com.board.boardsite.domain.constant;

import lombok.Getter;

public enum SearchType {

    TITLE("제목"),
    NICKNAME("닉네임");


    @Getter
    private final String description;

    SearchType(String description){
        this.description = description;
    }
}
