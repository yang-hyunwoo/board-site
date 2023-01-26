package com.board.boardsite.controller.travel;

import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.travel.TravelAgencyResponse;
import com.board.boardsite.dto.response.travel.TravelAgencyWithTravelAgencyListResponse;
import com.board.boardsite.service.travel.TravelAgencyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 여행사 클래스
 * @author cohouseol
 */

@Api(tags ={"여행사 관련 조회 Controller"})
@RestController
@RequestMapping("/api/trip/agency")
@RequiredArgsConstructor
public class TravelAgencyController {

    private final TravelAgencyService travelAgencyService;

    @GetMapping("/list")
    @ApiOperation(value = "여행사 목록  조회", notes = "여행 목록을 조회 한다.")
    public Response<Page<TravelAgencyResponse>> travelAgencyList(@RequestParam(required = false) String travelAgencyName,
                                                                 @PageableDefault(size=12,sort="name",direction= Sort.Direction.DESC) Pageable pageable){

        Page<TravelAgencyResponse> travelAgencyResponses = travelAgencyService.travelAgencyList(travelAgencyName , pageable).map(TravelAgencyResponse::from);
        return Response.success(travelAgencyResponses);
    }

    @GetMapping("/detail/{travelAgencyId}")
    @ApiOperation(value = "여행사 목록 상세  조회", notes = "여행 목록을 상세 조회 한다.")
    public Response<TravelAgencyResponse> travelAgencyDetail(@PathVariable Long travelAgencyId){
        TravelAgencyResponse travelAgencyDto= TravelAgencyResponse.from(travelAgencyService.travelAgencyDetail(travelAgencyId));
        return Response.success(travelAgencyDto);
    }

    @GetMapping("{travelAgencyId}")
    @ApiOperation(value = "여행사 여행 목록 조회", notes = "여행사 여행 목록을 조회 한다.")
    public Response<TravelAgencyWithTravelAgencyListResponse> travelAgencyWithTravelAgencyList(@PathVariable Long travelAgencyId){
        var travelDetail = TravelAgencyWithTravelAgencyListResponse.from(travelAgencyService.travelAgencyWithTravelAgencyList(travelAgencyId));
        return Response.success(travelDetail);
    }

    @GetMapping("/random-travelagency")
    @ApiOperation(value = "여행사 목록 랜덤  조회", notes = "여행 목록을 랜덤 조회 한다.")
    public Response<List<TravelAgencyResponse>> randomTravelAgencyList() {
        List<TravelAgencyResponse> travelAgencyResponse = travelAgencyService.travelAgencyRandomThree().stream().map(TravelAgencyResponse::from).collect(Collectors.toList());

        return Response.success(travelAgencyResponse);
    }



}
