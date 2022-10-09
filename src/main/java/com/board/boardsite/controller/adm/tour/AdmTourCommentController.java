package com.board.boardsite.controller.adm.tour;

import com.board.boardsite.dto.request.tour.TourCommentRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.tour.TourCommentResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.adm.tour.AdmTourCommentService;
import com.board.boardsite.service.tour.TourCommentService;
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
@RestController
@RequestMapping("/api/adm/tour")
@RequiredArgsConstructor
public class AdmTourCommentController {

    private final AdmTourCommentService admTourCommentService;

    /*
       게시판 댓글 리스트 조회
     */
    @GetMapping("/comment/{tourId}")
    public Response<Page<TourCommentResponse>> searchTourCommentsPage(@PathVariable Long tourId ,
                                                                         @PageableDefault(size=8,sort="createdAt",direction= Sort.Direction.DESC) Pageable pageable,
                                                                         @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {

        Page<TourCommentResponse> tourComments = admTourCommentService.searchTourCommentsPage(tourId,pageable).map(TourCommentResponse::from);
        return Response.success(tourComments);
    }

    /*
        게시판 댓글 생성
     */
    @PostMapping("/comment/new")
    public Response<Boolean> saveTourComment(@RequestBody TourCommentRequest tourCommentRequest,
                                                @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal){
        admTourCommentService.saveTourComment(tourCommentRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(true);
    }

    /*
        게시판 댓글 수정
     */
    @PutMapping("/comment/{tourCommentId}/form")
    public Response<Boolean> updateTourComment(@PathVariable Long tourCommentId,
                                                  @RequestBody TourCommentRequest tourCommentRequest,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admTourCommentService.updateTourComment(tourCommentId,tourCommentRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(true);
    }

    /*
        게시판 댓글 삭제(deleted = true) upd
     */
    @PutMapping("/comment/{tourCommentId}/delete")
    public Response<Boolean> deleteTourComment(@PathVariable Long tourCommentId,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admTourCommentService.deleteTourComment(tourCommentId,tripUserPrincipal.id());

        return Response.success(true);
    }


}
