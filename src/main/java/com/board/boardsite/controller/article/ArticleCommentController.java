package com.board.boardsite.controller.article;

import com.board.boardsite.dto.request.article.ArticleCommentRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.article.ArticleCommentResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.aritcle.ArticleCommentService;
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
 * 게시판 댓글 클래스
 * @author cohouseol
 */
@Api(tags ={"게시판 댓글 조회 Controller"})
@RestController
@RequestMapping("/api/trip/articles")
@RequiredArgsConstructor
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    /*
       게시판 댓글 리스트 조회
     */
    @GetMapping("/comment/{articleId}")
    @ApiOperation(value = "게시판 댓글 조회", notes = "게시판 댓글을 조회 한다.")
    public Response<Page<ArticleCommentResponse>> searchArticleCommentsPage(@PathVariable Long articleId ,
                                                                            @PageableDefault(size=8,sort="createdAt",direction= Sort.Direction.DESC) Pageable pageable,
                                                                            @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {

        Page<ArticleCommentResponse> articleComments = articleCommentService.searchArticleCommentsPage(articleId,pageable).map(ArticleCommentResponse::from);
        return Response.success(articleComments);
    }

    /*
        게시판 댓글 생성
     */
    @PostMapping("/comment/new")
    @ApiOperation(value = "게시판 댓글 생성", notes = "게시판 댓글을 생성 한다.")
    public Response<Boolean> saveArticleComment(@RequestBody ArticleCommentRequest articleCommentRequest,
                                                @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal){
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(true);
    }

    /*
        게시판 댓글 수정
     */
    @PutMapping("/comment/{articleCommentId}/form")
    @ApiOperation(value = "게시판 댓글 수정", notes = "게시판 댓글을 수정 한다.")
    public Response<Boolean> updateArticleComment(@PathVariable Long articleCommentId,
                                                  @RequestBody ArticleCommentRequest articleCommentRequest,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        articleCommentService.updateArticleComment(articleCommentId,articleCommentRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(true);
    }

    /*
        게시판 댓글 삭제(deleted = true) upd
     */
    @PutMapping("/comment/{articleCommentId}/delete")
    @ApiOperation(value = "게시판 댓글 삭제", notes = "게시판 댓글을 삭제 한다.")
    public Response<Boolean> deleteArticleComment(@PathVariable Long articleCommentId,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        articleCommentService.deleteArticleComment(articleCommentId,tripUserPrincipal.id());

        return Response.success(true);
    }


}
