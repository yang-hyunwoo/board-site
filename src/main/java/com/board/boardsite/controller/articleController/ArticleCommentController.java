package com.board.boardsite.controller.articleController;

import com.board.boardsite.dto.request.article.ArticleCommentRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.aritcle.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 게시판 댓글 클래스
 * @author cohouseol
 */
@RestController
@RequestMapping("/api/trip/articles")
@RequiredArgsConstructor
public class ArticleCommentController {

    private final ArticleCommentService articleCommentService;

    @PostMapping("/comment/new")
    public Response<Boolean> saveArticleComment(@RequestBody ArticleCommentRequest articleCommentRequest,
                                                @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal){
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(true);
    }

    @PutMapping("/comment/{articleCommentId}/form")
    public Response<Boolean> updateArticleComment(@PathVariable Long articleCommentId,
                                                  @RequestBody ArticleCommentRequest articleCommentRequest,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        articleCommentService.updateArticleComment(articleCommentId,articleCommentRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(true);
    }

    @PutMapping("/comment/{articleCommentId}/delete")
    public Response<Boolean> deleteArticleComment(@PathVariable Long articleCommentId,
                                                  @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        articleCommentService.deleteArticleComment(articleCommentId,tripUserPrincipal.id());
        return Response.success(true);

    }
}
