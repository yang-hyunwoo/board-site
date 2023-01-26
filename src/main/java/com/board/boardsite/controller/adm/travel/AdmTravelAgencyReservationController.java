package com.board.boardsite.controller.adm.travel;

import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.adm.travel.AdmTravelAgencyReservationResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.adm.travel.AdmTravelAgencyReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 여행 결제 및 조회 클래스
 * @author cohouseol
 */

@Api(tags ={"관리자 여행 결제 내역 조회 Controller"})
@RestController
@RequestMapping("/api/adm/reser")
@RequiredArgsConstructor
public class AdmTravelAgencyReservationController {

    private final AdmTravelAgencyReservationService admTravelAgencyReservationService;

    @GetMapping("/{travelAgencyListId}/purchaseList")
    @ApiOperation(value = "여행 결제 조회", notes = "여행 결제 조회 한다.")
    public Response<Page<AdmTravelAgencyReservationResponse>> getReservationList(@PathVariable Long travelAgencyListId,
                                                                                 @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal,
                                                                                 @PageableDefault(size=10,sort="createdAt",direction= Sort.Direction.DESC)Pageable pageable) {

        var response = admTravelAgencyReservationService.getReservationList(travelAgencyListId,tripUserPrincipal.travelAgencyId(),tripUserPrincipal.role() , pageable).map(AdmTravelAgencyReservationResponse::from);
        return Response.success(response);
    }


//    @PostMapping("/pay/complete")
//    public Response<Boolean> savePaymentTravelAgency(@RequestBody TravelAgencyReservationRequest travelAgencyReservationRequest,
//                                                     @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
//        boolean chk =  travelAgencyReservationService.travelAgencyReserSave(travelAgencyReservationRequest.toDto(tripUserPrincipal.toDto()));
//        return Response.success(chk);
//    }
//
//
//
//    @PostMapping("/pay/refund")
//    public Response<Boolean> payRefund(@RequestBody TravelAgencyRerservationRefundRequest travelAgencyRerservationRefundRequest) throws ParseException {
//        boolean chk = travelAgencyReservationService.iamPortRefund(travelAgencyRerservationRefundRequest);
//        return Response.success(chk);
//    }



}
