package com.board.boardsite.domain.constant;

import lombok.Getter;

public enum SearchAdmTravelListType {

    TITLE("제목"),
    AGENCYNAME("여행사"),
    CREATEDAT("작성일"),
    STARTAT("출발일");



    @Getter
    private final String description;

    SearchAdmTravelListType(String description){
        this.description = description;
    }
}
