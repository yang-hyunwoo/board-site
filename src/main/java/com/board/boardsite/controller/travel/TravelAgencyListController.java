package com.board.boardsite.controller.travel;

import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.travel.TravelAgencyListResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.travel.TravelAgencyListService;
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

@RestController
@RequestMapping("/api/trip/agency-trip")
@RequiredArgsConstructor
public class TravelAgencyListController {

    private  final TravelAgencyListService travelAgencyListService;

    @GetMapping("/triplist")
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
    public Response<Page<TravelAgencyListResponse>> travelAgencyTripListId(@PathVariable Long travelAgencyId ,
                                                                           @PageableDefault(size=3,sort="createdAt",direction= Sort.Direction.DESC) Pageable pageable){
        Page<TravelAgencyListResponse> travelAgencyList = travelAgencyListService.searchTravelAgencyPage(travelAgencyId , pageable).map(TravelAgencyListResponse::from);
        return Response.success(travelAgencyList);
    }

    @GetMapping("{travelAgencyListId}")
    public Response<TravelAgencyListResponse> travelAgencyTripDetail(@PathVariable Long travelAgencyListId) {
        var travelAgencyListDetail = TravelAgencyListResponse.from(travelAgencyListService.travelAgencyListDetail(travelAgencyListId));
        return Response.success(travelAgencyListDetail);
    }

    /*
    아임포트 결제시 인원수 증감
     */
    @GetMapping("/count/{travelAgencyListId}")
    public void travelAgencyTripCount(@PathVariable Long travelAgencyListId ,
                                      @RequestParam String operland,
                                      @RequestParam int count) {
            travelAgencyListService.travelAgencyOperland(travelAgencyListId , operland,count);
    }

    /*

     */
    @GetMapping("/trip-sort")
    public Response<List<TravelAgencyListResponse>> travelMainCarousel(){
        List<TravelAgencyListResponse> travelAgencyListResponses = travelAgencyListService.travelMainCarousel().stream().map(TravelAgencyListResponse::from).collect(Collectors.toList());
        return Response.success(travelAgencyListResponses);
    }

    /*
       여행 리스트 좋아요 클릭
     */
    @PostMapping("/like/{travelAgencyListId}")
    public Response<Boolean> travelAgencyListLike(@PathVariable Long travelAgencyListId,
                                     @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {

        travelAgencyListService.travelAgencyListLike(travelAgencyListId , tripUserPrincipal.id());
        return Response.success(true);
    }
}
