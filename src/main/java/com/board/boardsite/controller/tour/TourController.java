package com.board.boardsite.controller.tour;



import com.board.boardsite.domain.constant.SearchTourType;
import com.board.boardsite.domain.constant.SearchType;
import com.board.boardsite.dto.request.article.ArticleRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.article.ArticleResponse;
import com.board.boardsite.dto.response.article.ArticleWithCommentsResponse;
import com.board.boardsite.dto.response.tour.TourResponse;
import com.board.boardsite.dto.response.travel.TravelAgencyResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.aritcle.ArticleService;
import com.board.boardsite.service.tour.TourService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 관광지
 * @author cohouseol
 */
@RestController
@RequestMapping("/api/trip/tour")
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;


    @GetMapping("")
    public Response<Page<TourResponse>> articles(@RequestParam(required = false) SearchTourType searchType,
                                                 @RequestParam(required = false) String searchKeyWord,
                                                 @PageableDefault(size=9,sort="createdAt",direction= Sort.Direction.DESC)Pageable pageable) {

        Page<TourResponse> tours = tourService.tourSearchList(searchType,searchKeyWord,pageable).map(TourResponse::from);
        return Response.success(tours);
    }

    @GetMapping("/{tourId}")
    public Response<TourResponse> tourDetail(@PathVariable Long tourId,
                                                               @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        var tourDetail = TourResponse.from(tourService.tourDetail(tourId));
        return Response.success(tourDetail);
    }

    @GetMapping("/random")
    public Response<List<TourResponse>> randomTourList() {
        List<TourResponse> tourResponses = tourService.tourRandomThree().stream().map(TourResponse::from).collect(Collectors.toList());

        return Response.success(tourResponses);
    }





}
