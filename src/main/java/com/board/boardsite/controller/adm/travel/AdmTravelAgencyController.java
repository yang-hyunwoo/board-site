package com.board.boardsite.controller.adm.travel;

import com.board.boardsite.dto.request.adm.tour.TourRequest;
import com.board.boardsite.dto.request.adm.travel.TravelAgencyRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.tour.TourResponse;
import com.board.boardsite.dto.response.travel.TravelAgencyResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.adm.travel.AdmTravelAgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  여행사 관리자 컨트롤러
 *  @author cohouseol
 */

@RestController
@RequestMapping("/api/adm/agency")
@RequiredArgsConstructor
public class AdmTravelAgencyController {

    private final AdmTravelAgencyService admTravelAgencyService;


    /**
     * 여행사 리스트 조회
     * @param travelAgencyName
     * @param pageable
     * @return
     */
    @GetMapping("/list")
    public Response<Page<TravelAgencyResponse>> admTravelAgencyList(@RequestParam(required = false) String travelAgencyName,
                                                                    @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal,
                                                                 @PageableDefault(size=12,sort="name",direction= Sort.Direction.DESC) Pageable pageable){
        Page<TravelAgencyResponse> travelAgencyResponses = admTravelAgencyService.admTravelAgencyList(travelAgencyName,tripUserPrincipal.role(),tripUserPrincipal.travelAgencyId(), pageable).map(TravelAgencyResponse::from);
        return Response.success(travelAgencyResponses);
    }

    /**
     * 여행사 상세 조회
     * @param tourId
     * @param tripUserPrincipal
     * @return
     */
    @GetMapping("/{travelAgencyId}")
    public Response<TravelAgencyResponse> travelAgencyDetail(@PathVariable Long travelAgencyId ,  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal){
        TravelAgencyResponse travelAgencyDto= TravelAgencyResponse.from(admTravelAgencyService.travelAgencyDetail(travelAgencyId,tripUserPrincipal));
        return Response.success(travelAgencyDto);
    }

    /**
     * 여행사 삭제
     * @param agencyId
     * @param tripUserPrincipal
     * @return
     */
    @PutMapping("/{travelAgencyId}/delete")
    public Response<Boolean> deleteArticleComment(@PathVariable Long travelAgencyId,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admTravelAgencyService.deleteAgency(travelAgencyId,tripUserPrincipal.role());

        return Response.success(true);
    }

    /**
     * 여행사 재등록
     * @param agencyId
     * @param tripUserPrincipal
     * @return
     */
    @PutMapping("/{travelAgencyId}/re-delete")
    public Response<Boolean> reDeleteArticleComment(@PathVariable Long travelAgencyId,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admTravelAgencyService.reDeleteArticleComment(travelAgencyId,tripUserPrincipal.role());

        return Response.success(true);
    }


    /**
     * 여행사 신규 등록
     * @param travelAgencyRequest
     * @param tripUserPrincipal
     * @return
     */
    @PostMapping("/new-agency")
    public Response<Boolean> saveAgency(@Valid @RequestBody TravelAgencyRequest travelAgencyRequest ,
                                      @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        admTravelAgencyService.saveAgency(travelAgencyRequest.toDto());
        return Response.success(true);
    }


    /**
     * 여행사 수정
     * @param agencyId
     * @param travelAgencyRequest
     * @param tripUserPrincipal
     * @return
     */
    @PutMapping("/{agencyId}/form")
    public Response<Boolean> updateAgency(@PathVariable Long agencyId,
                                        @RequestBody TravelAgencyRequest travelAgencyRequest,
                                        @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admTravelAgencyService.updateAgency(agencyId,tripUserPrincipal.role(), travelAgencyRequest.toDto());
        return Response.success(true);
    }

}
