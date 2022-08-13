package com.board.boardsite.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {

    private String resultCode;
    private T result;

    public static Response<Void> error(String errorCode){
        return new Response<>(errorCode,null);
    }

    public static <T> Response<T> success(T result){
        return new Response<>("SUCCESS",result);
    }


}
