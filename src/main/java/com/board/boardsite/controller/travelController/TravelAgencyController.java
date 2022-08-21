package com.board.boardsite.controller.travelController;

import com.board.boardsite.domain.constant.SearchType;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.travel.TravelAgencyResponse;
import com.board.boardsite.dto.response.travel.TravelAgencyWithTravelAgencyListResponse;
import com.board.boardsite.dto.travel.TravelAgencyDto;
import com.board.boardsite.service.travel.TravelAgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/trip/agency")
@RequiredArgsConstructor
public class TravelAgencyController {

    private final TravelAgencyService travelAgencyService;

    @GetMapping("/list")
    public Response<Page<TravelAgencyResponse>> travelAgencyList(@RequestParam(required = false) String travelAgencyName,
                                                                 @PageableDefault(size=10,sort="name",direction= Sort.Direction.DESC) Pageable pageable){

        Page<TravelAgencyResponse> travelAgencyResponses = travelAgencyService.travelAgencyList(travelAgencyName , pageable).map(TravelAgencyResponse::from);
        return Response.success(travelAgencyResponses);
    }

    @GetMapping("/detail/{travelAgencyId}")
    public Response<TravelAgencyResponse> travelAgencyDetail(@PathVariable Long travelAgencyId){
        TravelAgencyResponse travelAgencyDto= TravelAgencyResponse.from(travelAgencyService.travelAgencyDetail(travelAgencyId));
        return Response.success(travelAgencyDto);
    }

    @GetMapping("{travelAgencyId}")
    public Response<TravelAgencyWithTravelAgencyListResponse> travelAgencyWithTravelAgencyList(@PathVariable Long travelAgencyId){
        var travelDetail = TravelAgencyWithTravelAgencyListResponse.from(travelAgencyService.travelAgencyWithTravelAgencyList(travelAgencyId));
        return Response.success(travelDetail);
    }

    @GetMapping("/random-travelagency")
    public Response<List<TravelAgencyResponse>> randomTravelAgencyList() {
        List<TravelAgencyResponse> travelAgencyResponse = travelAgencyService.travelAgencyRandomThree().stream().map(TravelAgencyResponse::from).collect(Collectors.toList());

        return Response.success(travelAgencyResponse);
    }


}
