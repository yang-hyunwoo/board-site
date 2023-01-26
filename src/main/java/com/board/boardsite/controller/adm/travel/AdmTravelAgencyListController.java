package com.board.boardsite.controller.adm.travel;


import com.board.boardsite.domain.constant.SearchAdmTravelListType;
import com.board.boardsite.dto.request.adm.travel.QrcodeUserUpdateRequest;
import com.board.boardsite.dto.request.adm.travel.TravelAgencyListRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.adm.travel.AdmTravelAgencyReservationResponse;
import com.board.boardsite.dto.response.travel.TravelAgencyListResponse;
import com.board.boardsite.dto.response.travel.TravelAgencyReservationResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.dto.travel.TravelAgencyReservationDto;
import com.board.boardsite.service.adm.travel.AdmTravelAgencyListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags ={"관리자 여행사 여행 목록 조회 Controller"})
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
    @ApiOperation(value = "여행사 여행 목록  조회", notes = "여행사 여행 목록을 조회 한다.")
    public Response<Page<TravelAgencyListResponse>> travelAgencyTripList(@RequestParam(required = false) SearchAdmTravelListType inputSearch,
                                                                     @RequestParam(required = false) SearchAdmTravelListType dateSearch,
                                                                     @RequestParam String startedAt,
                                                                     @RequestParam String endAt,
                                                                     @RequestParam(required = false) String input,
                                                                     @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal,
                                                                     @PageableDefault(size=10)Pageable pageable){

        Page<TravelAgencyListResponse> travelAgencyListResponses = admTravelAgencyListService.travelAgencyTripList(inputSearch,dateSearch,startedAt,endAt,input ,tripUserPrincipal, pageable).map(TravelAgencyListResponse::from);
        return Response.success(travelAgencyListResponses);
    }

    /**
     * 여행 리스트 종료
     * @param travelAgencyListId
     * @param tripUserPrincipal
     * @return
     */
    @PutMapping("/{travelAgencyListId}/delete")
    @ApiOperation(value = "여행사 여행 목록  삭제", notes = "여행사 여행 목록을 삭제 한다.")
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
    @ApiOperation(value = "여행사 여행 목록  재등록 ", notes = "여행사 여행 목록을 재등록 한다.")
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
    @ApiOperation(value = "여행사 여행 목록  신규 등록 ", notes = "여행사 여행 목록을 신규 등록 한다.")
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
    @ApiOperation(value = "여행 목록 상세 조회", notes = "여행 목록을 상세 조회 한다.")
    public Response<TravelAgencyListResponse> travelAgencyListDetail(@PathVariable Long travelAgencyListId,
                                                                     @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        var travelAgencyListDetail = TravelAgencyListResponse.from(admTravelAgencyListService.travelAgencyListDetail(travelAgencyListId,tripUserPrincipal));
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
    @ApiOperation(value = "여행사 여행 목록  수정 한다 ", notes = "여행사 여행 목록을 수정 한다.")
    public Response<Boolean> updateTravelAgencyList(@PathVariable Long travelAgencyListId,
                                        @RequestBody TravelAgencyListRequest travelAgencyListRequest,
                                        @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admTravelAgencyListService.updateTravelAgencyList(travelAgencyListId,travelAgencyListRequest.toDto());
        return Response.success(true);
    }


    @PutMapping("/{travelAgencyListId}/{sort}/sort")
    @ApiOperation(value = "여행사 여행 목록  정렬 ", notes = "메인 화면에 보이는 목록을 설정 한다.")
    public Response<Boolean> sortTravelAgencyList(@PathVariable Long travelAgencyListId,
                                                  @PathVariable  int sort ,
                                                      @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admTravelAgencyListService.sortTravelAgencyList(travelAgencyListId,sort);

        return Response.success(true);
    }


    /**
     * 금일 qr가능한 여행 리스트 목록
     * @param startedAt
     * @param endAt
     * @param tripUserPrincipal
     * @param pageable
     * @return
     */
    @GetMapping("/qrcode/triplist")
    @ApiOperation(value = "금일 qr 가능한 여행 리스트 목록  ", notes = "금일 qr 가능한 여행 리스트 목록을 조회 한다.")
    public Response<Page<TravelAgencyListResponse>> qrcodeTravelAgencyTripList(@RequestParam String startedAt,
                                                                         @RequestParam String endAt,
                                                                         @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal,
                                                                         @PageableDefault(size=10)Pageable pageable){

        Page<TravelAgencyListResponse> travelAgencyListResponses = admTravelAgencyListService.qrcodeTravelAgencyTripList(startedAt,endAt,tripUserPrincipal, pageable).map(TravelAgencyListResponse::from);
        return Response.success(travelAgencyListResponses);
    }

    /**
     * qr코드 확인 상세
     * @param travelAgencyListId
     * @param tripUserPrincipal
     * @return
     */
    @GetMapping("/qrcode/{travelAgencyListId}")
    @ApiOperation(value = "qr코드 확인 상세 ", notes = "qr코드 확인 상세 조회.")
    public Response<TravelAgencyListResponse> qrcodeTravelAgencyListDetail(@PathVariable Long travelAgencyListId,
                                                                           @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        var travelAgencyListDetail = TravelAgencyListResponse.from(admTravelAgencyListService.qrcodeTravelAgencyListDetail(travelAgencyListId,tripUserPrincipal));
        return Response.success(travelAgencyListDetail);
    }

    /**
     * qr코드 화면 유저 리스트 조회
     * @param travelAgencyListId
     * @param tripUserPrincipal
     * @param pageable
     * @return
     */
    @GetMapping("/qrcode/{travelAgencyListId}/userlist")
    @ApiOperation(value = "qr코드 화면 유저 리스트 조회 ", notes = "qr코드 화면 유저 리스트 조회 한다.")
    public Response<Page<AdmTravelAgencyReservationResponse>> qrcodeTravelUserList(@PathVariable Long travelAgencyListId,
                                                                                   @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal,
                                                                                   @PageableDefault(size=10)Pageable pageable) {
        var travelAgencyUserList =admTravelAgencyListService.qrcodeTravelUserList(travelAgencyListId,pageable).map(AdmTravelAgencyReservationResponse::from);
        return Response.success(travelAgencyUserList);
    }



    /**
     *
     * @param TravelAgencyListRequest
     * @return
     */
    @PutMapping("/qrcode/qr-upd")
    @ApiOperation(value = "qr코드 수정 ", notes = "qr코드 수정 한다.")
    public Response<Boolean> qrcodeUserUpd(@RequestBody QrcodeUserUpdateRequest qrcodeUserUpdateRequest) {
        admTravelAgencyListService.qrcodeUserUpd(qrcodeUserUpdateRequest);
        return Response.success(true);
    }
}
