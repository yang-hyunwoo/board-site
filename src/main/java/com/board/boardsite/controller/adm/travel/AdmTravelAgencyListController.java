package com.board.boardsite.controller.adm.travel;


import com.board.boardsite.domain.constant.SearchAdmTravelListType;
import com.board.boardsite.dto.request.adm.tour.TourRequest;
import com.board.boardsite.dto.request.adm.travel.TravelAgencyListRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.travel.TravelAgencyListResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.adm.travel.AdmTravelAgencyListService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/adm/agency-trip")
@RequiredArgsConstructor
public class AdmTravelAgencyListController {

    private  final AdmTravelAgencyListService admTravelAgencyListService;


    /**
     * 여행 리스트 조회
     * @param inputSearch
     * @param dateSearch
     * @param startedAt
     * @param endAt
     * @param input
     * @param pageable
     * @return
     */
    @GetMapping("/triplist")
    public Response<Page<TravelAgencyListResponse>> travelAgencyTripList(@RequestParam(required = false) SearchAdmTravelListType inputSearch,
                                                                     @RequestParam(required = false) SearchAdmTravelListType dateSearch,
                                                                     @RequestParam String startedAt,
                                                                     @RequestParam String endAt,
                                                                     @RequestParam(required = false) String input,
                                                                     @PageableDefault(size=10)Pageable pageable){

        Page<TravelAgencyListResponse> travelAgencyListResponses = admTravelAgencyListService.travelAgencyTripList(inputSearch,dateSearch,startedAt,endAt,input , pageable).map(TravelAgencyListResponse::from);
        return Response.success(travelAgencyListResponses);
    }

    /**
     * 여행 리스트 종료
     * @param travelAgencyListId
     * @param tripUserPrincipal
     * @return
     */
    @PutMapping("/{travelAgencyListId}/delete")
    public Response<Boolean> deleteTravelAgencyList(@PathVariable Long travelAgencyListId,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admTravelAgencyListService.deleteTravelAgencyList(travelAgencyListId);

        return Response.success(true);
    }


    /**
     * 여행 리스트 재등록
     * @param travelAgencyListId
     * @param tripUserPrincipal
     * @return
     */
    @PutMapping("/{travelAgencyListId}/re-delete")
    public Response<Boolean> reDeleteTravelAgencyList(@PathVariable Long travelAgencyListId,
                                                    @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admTravelAgencyListService.reDeleteTravelAgencyList(travelAgencyListId);

        return Response.success(true);
    }

    /**
     * 여행 리스트 신규 등록
     * @param TravelAgencyListRequest
     * @return
     */
    @PostMapping("/new-travel-agency-list")
    public Response<Boolean> saveTravelAgencyList(@Valid @RequestBody TravelAgencyListRequest TravelAgencyListRequest) {
        admTravelAgencyListService.saveTravelAgencyList(TravelAgencyListRequest.toDto());
        return Response.success(true);
    }


    /**
     * 여행사 라스트 상세 조회
     * @param travelAgencyListId
     * @return
     */
    @GetMapping("{travelAgencyListId}")
    public Response<TravelAgencyListResponse> travelAgencyListDetail(@PathVariable Long travelAgencyListId) {
        var travelAgencyListDetail = TravelAgencyListResponse.from(admTravelAgencyListService.travelAgencyListDetail(travelAgencyListId));
        return Response.success(travelAgencyListDetail);
    }

    /**
     * 여행사 리스트 수정
     * @param travelAgencyListId
     * @param travelAgencyListRequest
     * @param tripUserPrincipal
     * @return
     */
    @PutMapping("/{travelAgencyListId}/form")
    public Response<Boolean> updateTravelAgencyList(@PathVariable Long travelAgencyListId,
                                        @RequestBody TravelAgencyListRequest travelAgencyListRequest,
                                        @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admTravelAgencyListService.updateTravelAgencyList(travelAgencyListId,travelAgencyListRequest.toDto());
        return Response.success(true);
    }


    @PutMapping("/{travelAgencyListId}/{sort}/sort")
    public Response<Boolean> sortTravelAgencyList(@PathVariable Long travelAgencyListId,
                                                  @PathVariable  int sort ,
                                                      @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        System.out.println("111111111111111111");
        admTravelAgencyListService.sortTravelAgencyList(travelAgencyListId,sort);

        return Response.success(true);
    }
}
