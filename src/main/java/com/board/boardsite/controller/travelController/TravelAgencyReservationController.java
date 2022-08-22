package com.board.boardsite.controller.travelController;

import com.board.boardsite.domain.travel.TravelAgencyReservation;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.request.travel.TravelAgencyReservationRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.repository.travel.TravelAgencyReservationRepository;
import com.board.boardsite.service.travel.TravelAgencyReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 여행 결제 및 조회 클래스
 * @author cohouseol
 */

@RestController
@RequestMapping("/api/trip/reser")
@RequiredArgsConstructor
public class TravelAgencyReservationController {

    private final TravelAgencyReservationService travelAgencyReservationService;

    @PostMapping("/pay/complete")
    public Response<Boolean> savePaymentTravelAgency(@RequestBody TravelAgencyReservationRequest travelAgencyReservationRequest,
                                                     @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
       boolean chk =  travelAgencyReservationService.travelAgencyReserSave(travelAgencyReservationRequest.toDto(tripUserPrincipal.toDto()));
    return Response.success(chk);
    }




}
