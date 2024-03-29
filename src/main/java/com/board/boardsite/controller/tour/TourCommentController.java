package com.board.boardsite.controller.tour;

import com.board.boardsite.dto.request.tour.TourCommentRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.tour.TourCommentResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.tour.TourCommentService;
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
 * 관광지 댓글 클래스
 * @author cohouseol
 */
@Api(tags ={"여행 목록 댓글 조회 Controller"})
@RestController
@RequestMapping("/api/trip/tour")
@RequiredArgsConstructor
public class TourCommentController {

    private final TourCommentService tourCommentService;

    /*
       게시판 댓글 리스트 조회
     */
    @GetMapping("/comment/{tourId}")
    @ApiOperation(value = "여행 목록 댓글 조회", notes = "여행 목록 댓글을 조회 한다.")
    public Response<Page<TourCommentResponse>> searchTourCommentsPage(@PathVariable Long tourId ,
                                                                         @PageableDefault(size=8,sort="createdAt",direction= Sort.Direction.DESC) Pageable pageable,
                                                                         @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {

        Page<TourCommentResponse> tourComments = tourCommentService.searchTourCommentsPage(tourId,pageable).map(TourCommentResponse::from);
        return Response.success(tourComments);
    }

    /*
        게시판 댓글 생성
     */
    @PostMapping("/comment/new")
    @ApiOperation(value = "여행 목록 댓글 생성", notes = "여행 목록 댓글을 생성 한다.")
    public Response<Boolean> saveTourComment(@RequestBody TourCommentRequest tourCommentRequest,
                                                @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal){
        tourCommentService.saveTourComment(tourCommentRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(true);
    }

    /*
        게시판 댓글 수정
     */
    @PutMapping("/comment/{tourCommentId}/form")
    @ApiOperation(value = "여행 목록 댓글 수정", notes = "여행 목록 댓글을 수정 한다.")
    public Response<Boolean> updateTourComment(@PathVariable Long tourCommentId,
                                                  @RequestBody TourCommentRequest tourCommentRequest,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        tourCommentService.updateTourComment(tourCommentId,tourCommentRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(true);
    }

    /*
        게시판 댓글 삭제(deleted = true) upd
     */
    @PutMapping("/comment/{tourCommentId}/delete")
    @ApiOperation(value = "여행 목록 댓글 삭제", notes = "여행 목록 댓글을 삭제 한다.")
    public Response<Boolean> deleteTourComment(@PathVariable Long tourCommentId,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        tourCommentService.deleteTourComment(tourCommentId,tripUserPrincipal.id());

        return Response.success(true);
    }


}
