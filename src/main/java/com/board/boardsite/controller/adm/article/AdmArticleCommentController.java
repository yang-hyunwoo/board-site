package com.board.boardsite.controller.adm.article;

import com.board.boardsite.dto.request.article.ArticleCommentRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.article.ArticleCommentResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.adm.article.AdmArticleCommentService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 게시판 댓글 클래스
 * @author cohouseol
 */
@Api(tags ={"관리자가 게시판 댓글 관리하는 Controller"})
@RestController
@RequestMapping("/api/adm/articles")
@RequiredArgsConstructor
public class AdmArticleCommentController {

    private final AdmArticleCommentService admArticleCommentService;

    /*
       게시판 댓글 리스트 조회
     */
    @GetMapping("/comment/{articleId}")
    public Response<Page<ArticleCommentResponse>> searchArticleCommentsPage(@PathVariable Long articleId ,
                                                                            @PageableDefault(size=8,sort="createdAt",direction= Sort.Direction.DESC) Pageable pageable,
                                                                            @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {

        Page<ArticleCommentResponse> articleComments = admArticleCommentService.searchArticleCommentsPage(articleId,pageable).map(ArticleCommentResponse::from);
        return Response.success(articleComments);
    }

    /*
        게시판 댓글 생성
     */
    @PostMapping("/comment/new")
    public Response<Boolean> saveArticleComment(@RequestBody ArticleCommentRequest articleCommentRequest,
                                                @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal){
        admArticleCommentService.saveArticleComment(articleCommentRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(true);
    }

    /*
        게시판 댓글 수정
     */
    @PutMapping("/comment/{articleCommentId}/form")
    public Response<Boolean> updateArticleComment(@PathVariable Long articleCommentId,
                                                  @RequestBody ArticleCommentRequest articleCommentRequest,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admArticleCommentService.updateArticleComment(articleCommentId,articleCommentRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(true);
    }

    /*
        게시판 댓글 삭제(deleted = true) upd
     */
    @PutMapping("/comment/{articleCommentId}/delete")
    public Response<Boolean> deleteArticleComment(@PathVariable Long articleCommentId,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        admArticleCommentService.deleteArticleComment(articleCommentId);

        return Response.success(true);
    }


}
