package com.board.boardsite.controller;


import com.board.boardsite.domain.TripUser;
import com.board.boardsite.dto.TripUserDto;
import com.board.boardsite.dto.request.TripUserJoinRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.TripUserJoinResponse;
import com.board.boardsite.service.TripUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trip/users")
@RequiredArgsConstructor
public class TripUserController {

    private final TripUserService tripUserService;

    @PostMapping("/join")
    public Response<TripUserJoinResponse> join(@RequestBody TripUserJoinRequest request) {
        TripUserDto tripUserDto  = tripUserService.join(request.toDto());
        return Response.success(TripUserJoinResponse.from(tripUserDto));
    }



}
