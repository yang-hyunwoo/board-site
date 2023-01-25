package com.board.boardsite.controller.tour;



import com.board.boardsite.domain.constant.SearchTourType;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.tour.TourResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.tour.TourService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 관광지
 * @author cohouseol
 */
@Api(tags ={"여행 목록 정보 조회 Controller"})
@RestController
@RequestMapping("/api/trip/tour")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;


    @GetMapping("")
    @ApiOperation(value = "여행 목록 조회", notes = "여행 목록을 조회 한다.")
    public Response<Page<TourResponse>> articles(@RequestParam(required = false) SearchTourType searchType,
                                                 @RequestParam(required = false) String searchKeyWord,
                                                 @PageableDefault(size=9,sort="createdAt",direction= Sort.Direction.DESC)Pageable pageable) {

        Page<TourResponse> tours = tourService.tourSearchList(searchType,searchKeyWord,pageable).map(TourResponse::from);
        return Response.success(tours);
    }

    @GetMapping("/{tourId}")
    @ApiOperation(value = "여행 목록 상세 조회", notes = "여행 목록을 상세 조회 한다.")
    public Response<TourResponse> tourDetail(@PathVariable Long tourId,
                                                               @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        var tourDetail = TourResponse.from(tourService.tourDetail(tourId,tripUserPrincipal==null?null:tripUserPrincipal.role()));
        return Response.success(tourDetail);
    }

    @GetMapping("/random")
    @ApiOperation(value = "여행 목록 랜덤 조회", notes = "여행 목록을 랜덤 으로 3개 조회한다.")
    public Response<List<TourResponse>> randomTourList() {
        List<TourResponse> tourResponses = tourService.tourRandomThree().stream().map(TourResponse::from).collect(Collectors.toList());

        return Response.success(tourResponses);
    }





}
