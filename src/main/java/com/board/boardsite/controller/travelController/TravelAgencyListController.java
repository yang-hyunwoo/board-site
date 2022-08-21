package com.board.boardsite.controller.travelController;

import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.travel.TravelAgencyListResponse;
import com.board.boardsite.dto.response.travel.TravelAgencyResponse;
import com.board.boardsite.service.travel.TravelAgencyListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trip/agency-trip")
@RequiredArgsConstructor
public class TravelAgencyListController {

    private  final TravelAgencyListService travelAgencyListService;

    @GetMapping("/triplist")
    public Response<Page<TravelAgencyListResponse>> travelAgencyTripList(@RequestParam(required = false) String travelAgencyTitleName,
                                                                         @PageableDefault(size=9)@SortDefault.SortDefaults(
                                                                         { @SortDefault(sort="readCount" , direction = Sort.Direction.ASC),
                                                                           @SortDefault(sort="title" , direction = Sort.Direction.ASC)}
                                                                         ) Pageable pageable){

        Page<TravelAgencyListResponse> travelAgencyListResponses = travelAgencyListService.travelAgencyTripList(travelAgencyTitleName , pageable).map(TravelAgencyListResponse::from);
        return Response.success(travelAgencyListResponses);
    }

    @GetMapping("{travelAgencyListId}")
    public Response<TravelAgencyListResponse> travelAgencyTripDetail(@PathVariable Long travelAgencyListId) {
        var travelAgencyListDetail = TravelAgencyListResponse.from(travelAgencyListService.travelAgencyListDetail(travelAgencyListId));
        return Response.success(travelAgencyListDetail);
    }


}
