package com.board.boardsite.controller.travel;

import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.travel.TravelAgencyLikeResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.travel.TravelAgencyListLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags ={"여행 목록 좋아요 Controller"})
@RestController
@RequestMapping("/api/trip/like")
@RequiredArgsConstructor
public class TravelAgencyListLikeController {

    private final TravelAgencyListLikeService travelAgencyListLikeService;

    @GetMapping("/list")
    @ApiOperation(value = "여행 좋아요 목록 조회", notes = "사용자 마다 여행 좋아요 목록을 조회 한다.")
    public Response<Page<TravelAgencyLikeResponse>> travelAgencyList(@PageableDefault(size=9,sort="createdAt",direction= Sort.Direction.DESC) Pageable pageable,
                                                                     @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal){
       var pageTravel = travelAgencyListLikeService.travelAgencyLikeList(tripUserPrincipal.id(), pageable).map(TravelAgencyLikeResponse::from);
        return Response.success(pageTravel);
    }
}
