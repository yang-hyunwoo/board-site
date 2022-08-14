package com.board.boardsite.dto.request.user;

public record EmailAuthRequest(
        String email,
        String authToken,
        Boolean expired
) {
}
