package com.board.boardsite.controller.travel;

import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.travel.TravelAgencyListResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.travel.TravelAgencyListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 여행사 여행 목록 리스트 클래스
 * @author cohouseol
 */
@Api(tags ={"여행사 여행 목록 조회 Controller"})
@RestController
@RequestMapping("/api/trip/agency-trip")
@RequiredArgsConstructor
public class TravelAgencyListController {

    private  final TravelAgencyListService travelAgencyListService;

    @GetMapping("/triplist")
    @ApiOperation(value = "여행사 여행 목록  조회", notes = "여행사 여행 목록을 조회 한다.")
    public Response<Page<TravelAgencyListResponse>> travelAgencyTripList(@RequestParam(required = false) String travelAgencyTitleName,
                                                                         @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal,
                                                                         @PageableDefault(size=9)@SortDefault.SortDefaults(
                                                                         { @SortDefault(sort="readCount" , direction = Sort.Direction.ASC),
                                                                           @SortDefault(sort="title" , direction = Sort.Direction.ASC)}
                                                                         ) Pageable pageable){
        Page<TravelAgencyListResponse> travelAgencyListResponses = travelAgencyListService.travelAgencyTripList(travelAgencyTitleName,tripUserPrincipal, pageable).map(TravelAgencyListResponse::from);
        return Response.success(travelAgencyListResponses);
    }

    @GetMapping("/triplist/{travelAgencyId}")
    @ApiOperation(value = "여행사 별 여행 목록   조회", notes = "여행사 별 여행 목록을 조회 한다.")
    public Response<Page<TravelAgencyListResponse>> travelAgencyTripListId(@PathVariable Long travelAgencyId ,
                                                                           @PageableDefault(size=3,sort="createdAt",direction= Sort.Direction.DESC) Pageable pageable){
        Page<TravelAgencyListResponse> travelAgencyList = travelAgencyListService.searchTravelAgencyPage(travelAgencyId , pageable).map(TravelAgencyListResponse::from);
        return Response.success(travelAgencyList);
    }

    @GetMapping("{travelAgencyListId}")
    @ApiOperation(value = "여행 목록   조회", notes = "여행 목록을 조회 한다.")
    public Response<TravelAgencyListResponse> travelAgencyTripDetail(@PathVariable Long travelAgencyListId) {
        var travelAgencyListDetail = TravelAgencyListResponse.from(travelAgencyListService.travelAgencyListDetail(travelAgencyListId));
        return Response.success(travelAgencyListDetail);
    }

    /*
    아임포트 결제시 인원수 증감
     */
    @GetMapping("/count/{travelAgencyListId}")
    @ApiOperation(value = "여행 결제시 인원수 증감", notes = "여행 결제시 인원수 증감.")
    public void travelAgencyTripCount(@PathVariable Long travelAgencyListId ,
                                      @RequestParam String operland,
                                      @RequestParam int count) {
            travelAgencyListService.travelAgencyOperland(travelAgencyListId , operland,count);
    }

    /*

     */
    @GetMapping("/trip-sort")
    @ApiOperation(value = "(관리자) 여행 목록 조회", notes = "관리자 페이지 메인 화면 여행 목록 조회를 한다.")
    public Response<List<TravelAgencyListResponse>> travelMainCarousel(){
        List<TravelAgencyListResponse> travelAgencyListResponses = travelAgencyListService.travelMainCarousel().stream().map(TravelAgencyListResponse::from).collect(Collectors.toList());
        return Response.success(travelAgencyListResponses);
    }

    /*
       여행 리스트 좋아요 클릭
     */
    @PostMapping("/like/{travelAgencyListId}")
    @ApiOperation(value = "여행 리스트 좋아요 클릭 ", notes = "여행 리스트 좋아요 클릭을 한다.")
    public Response<Boolean> travelAgencyListLike(@PathVariable Long travelAgencyListId,
                                     @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {

        travelAgencyListService.travelAgencyListLike(travelAgencyListId , tripUserPrincipal.id());
        return Response.success(true);
    }
}
