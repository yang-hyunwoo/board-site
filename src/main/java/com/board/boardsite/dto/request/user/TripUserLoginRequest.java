package com.board.boardsite.dto.request.user;

public record TripUserLoginRequest(
        String email,
        String password
)
{
}
