package com.board.boardsite.domain.constant;

import lombok.Getter;

public enum SearchTourType {

    TITLE("제목"),
    CITY("도시");


    @Getter
    private final String description;

    SearchTourType(String description){
        this.description = description;
    }
}
