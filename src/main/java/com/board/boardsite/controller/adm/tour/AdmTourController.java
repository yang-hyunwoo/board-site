package com.board.boardsite.controller.adm.tour;

import com.board.boardsite.domain.constant.SearchTourType;
import com.board.boardsite.dto.request.adm.tour.TourRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.tour.TourResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.adm.tour.AdmTourService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 관광지 관리자 controller
 * @author cohouseol
 */
@RestController
@RequestMapping("/api/adm/tour")
@RequiredArgsConstructor
public class AdmTourController {

    private final AdmTourService admTourService;


    /**
     * 관광지 리스트 조회
     * @param searchType
     * @param searchKeyWord
     * @param pageable
     * @return
     */
    @GetMapping("")
    public Response<Page<TourResponse>> articles(@RequestParam(required = false) SearchTourType searchType,
                                                 @RequestParam(required = false) String searchKeyWord,
                                                 @PageableDefault(size=9,sort="createdAt",direction= Sort.Direction.DESC) Pageable pageable) {
        Page<TourResponse> tours = admTourService.tourList(searchType,searchKeyWord,pageable).map(TourResponse::from);
        return Response.success(tours);
    }



    /**
     *  관광지  삭제
     * @param tourId
     * @param tripUserPrincipal
     * @return
     */
    @PutMapping("/{tourId}/delete")
    public Response<Boolean> deleteArticleComment(@PathVariable Long tourId,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admTourService.deleteTourList(tourId,tripUserPrincipal.role());

        return Response.success(true);
    }

    /**
     * 관광지  재등록
     * @param tourId
     * @param tripUserPrincipal
     * @return
     */
    @PutMapping("/{tourId}/re-delete")
    public Response<Boolean> reDeleteArticleComment(@PathVariable Long tourId,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admTourService.reDeleteArticleComment(tourId,tripUserPrincipal.role());

        return Response.success(true);
    }

    /**
     * 관광지  신규 등록
     * @param TourRequest
     * @param tripUserPrincipal
     * @return
     */
    @PostMapping("/new-tour")
    public Response<Boolean> saveTour(@Valid @RequestBody TourRequest TourRequest ,
                                      @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        admTourService.saveTour(TourRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(true);
    }

    @GetMapping("/valid/{tourId}")
    public Response<TourResponse> articleValidDetail(@PathVariable Long tourId,
                                                        @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {

        var tourDetail = TourResponse.from(admTourService.tourDetail(tourId , tripUserPrincipal.role()));

        return Response.success(tourDetail);
    }

    /**
     * 관광지 수정
     * @param tourId
     * @param tourRequest
     * @param tripUserPrincipal
     * @return
     */
    @PutMapping("/{tourId}/form")
    public Response<Boolean> updateTour(@PathVariable Long tourId,
                                               @RequestBody TourRequest tourRequest,
                                               @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admTourService.updateTour(tourId,tourRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(true);
    }
}
