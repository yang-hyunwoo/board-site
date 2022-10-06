package com.board.boardsite.controller.adm.dashboard;

import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.adm.dashboard.TravelListCountDto;
import com.board.boardsite.dto.response.travel.TravelAgencyListResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.adm.dashboard.DashBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/adm/dashboard")
@RequiredArgsConstructor
public class DashBoardController {

    private  final DashBoardService dashBoardService;

    @GetMapping("/triplist")
    public Response<List<TravelAgencyListResponse>> travelAgencyTripList(@AuthenticationPrincipal TripUserPrincipal tripUserPrincipal){

        var travelAgencyListResponses = dashBoardService.travelAgencyTripList(tripUserPrincipal.getAuthorities(),tripUserPrincipal.travelAgencyId())
                .stream().map(TravelAgencyListResponse::from).collect(Collectors.toList());

        return Response.success(travelAgencyListResponses);
    }

    @GetMapping("/pay-count")
    public Response<List<TravelListCountDto>> travelAgencyTripPayCount(@AuthenticationPrincipal TripUserPrincipal tripUserPrincipal){
            return Response.success(dashBoardService.travelAgencyTripPayCount(tripUserPrincipal.travelAgencyId(),tripUserPrincipal.getAuthorities()));
    }



}
