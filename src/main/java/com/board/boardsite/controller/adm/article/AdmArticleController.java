package com.board.boardsite.controller.adm.article;



import com.board.boardsite.domain.constant.SearchType;
import com.board.boardsite.dto.request.article.ArticleRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.article.ArticleResponse;
import com.board.boardsite.dto.response.article.ArticleWithCommentsResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.adm.article.AdmArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 게시판 클래스
 * @author cohouseol
 */
@RestController
@Api(tags ={"관리자 게시판 정보 조회 Controller"})
@RequestMapping("/api/adm/articles")
@RequiredArgsConstructor
public class AdmArticleController {

    private final AdmArticleService admArticleService;


    @GetMapping("")
    @ApiOperation(value = "게시판 조회", notes = "게시판 목록을 조회 한다.")
    public Response<Page<ArticleResponse>> articles(@RequestParam(required = false) SearchType searchType,
                                              @RequestParam(required = false) String searchKeyWord,
                                              @PageableDefault(size=10,sort="createdAt",direction= Sort.Direction.DESC)Pageable pageable) {

        Page<ArticleResponse> articles = admArticleService.articleSearchList(searchType,searchKeyWord,pageable).map(ArticleResponse::from);
        return Response.success(articles);
    }

    @GetMapping("/{articleId}")
    @ApiOperation(value = "게시판 상세 조회", notes = "게시판 상세 조회 한다.")
    public Response<ArticleWithCommentsResponse> articleDetail(@PathVariable Long articleId,
                                                               @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        var articleDetail = ArticleWithCommentsResponse.from(admArticleService.getArticleWithComment(articleId),tripUserPrincipal);
        return Response.success(articleDetail);
    }

    @GetMapping("/valid/{articleId}")
    @ApiOperation(value = "게시판 상세 유효성 조회", notes = "게시판 목록의 사용자 정보를 유효성 검사를 한다.")
    public Response<ArticleResponse> articleValidDetail(@PathVariable Long articleId,
                                                               @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {

        var articleValidDetail = ArticleResponse.from(admArticleService.articleValidDetail(articleId , tripUserPrincipal.id()));

        return Response.success(articleValidDetail);
    }

    @PostMapping("/new-article")
    @ApiOperation(value = "게시판 생성", notes = "게시판을 생성한다.")
    public Response<Boolean> saveArticle(@Valid @RequestBody ArticleRequest articleRequest ,
                                         @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        admArticleService.saveArticle(articleRequest.toDto(tripUserPrincipal.toDto()));
    return Response.success(true);
    }

    @PostMapping("/{articleId}/form")
    @ApiOperation(value = "게시판 수정", notes = "게시판을 수정한다.")
    public Response<Boolean> updateArticle(@PathVariable Long articleId ,
                                           @Valid @RequestBody ArticleRequest articleRequest ,
                                           @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {

        admArticleService.updateArticle(articleId,articleRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(true);
    }

    @PutMapping("/{articleId}/delete")
    @ApiOperation(value = "게시판 삭제", notes = "게시판을 삭제한다.")
    public Response<Boolean> deleteArticle(@PathVariable Long articleId)
    {
        admArticleService.deleteArticle(articleId );
        return Response.success(true);
    }

    @PutMapping("/{articleId}/re-delete")
    @ApiOperation(value = "게시판 삭제 복구", notes = "게시판이 삭제된 것을 복구 한다.")
    public Response<Boolean> reDeleteArticle(@PathVariable Long articleId)
    {
        admArticleService.reDeleteArticle(articleId);
        return Response.success(true);
    }
}
