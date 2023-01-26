package com.board.boardsite.controller.travel;

import com.board.boardsite.dto.request.travel.TravelAgencyRerservationRefundRequest;
import com.board.boardsite.dto.request.travel.TravelAgencyReservationRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.travel.TravelAgencyReservationResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.travel.TravelAgencyReservationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
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
@Api(tags ={"여행 결제 및 조회 Controller"})
@RestController
@RequestMapping("/api/trip/reser")
@RequiredArgsConstructor
public class TravelAgencyReservationController {

    private final TravelAgencyReservationService travelAgencyReservationService;

    @PostMapping("/pay/complete")
    @ApiOperation(value = "결제 완료", notes = "결제 완료시.")
    public Response<Boolean> savePaymentTravelAgency(@RequestBody TravelAgencyReservationRequest travelAgencyReservationRequest,
                                                     @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        boolean chk =  travelAgencyReservationService.travelAgencyReserSave(travelAgencyReservationRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(chk);
    }

    @GetMapping("/purchaseList")
    @ApiOperation(value = "결제 목록", notes = "결제 목록을 조회 한다.")
    public Response<Page<TravelAgencyReservationResponse>> getReservationList(@AuthenticationPrincipal TripUserPrincipal tripUserPrincipal,
                                                                              @PageableDefault(size=10,sort="createdAt",direction= Sort.Direction.DESC)Pageable pageable) {

        var response = travelAgencyReservationService.getReservationList(tripUserPrincipal.id(),pageable).map(TravelAgencyReservationResponse::from);
        return Response.success(response);
    }

    @PostMapping("/pay/refund")
    @ApiOperation(value = "결제 취소", notes = "결제 취소시.")
    public Response<Boolean> payRefund(@RequestBody TravelAgencyRerservationRefundRequest travelAgencyRerservationRefundRequest) throws ParseException {
        boolean chk = travelAgencyReservationService.iamPortRefund(travelAgencyRerservationRefundRequest);
        return Response.success(chk);
    }



}
