package com.board.boardsite.controller.article;



import com.board.boardsite.domain.constant.SearchType;
import com.board.boardsite.dto.request.article.ArticleRequest;
import com.board.boardsite.dto.response.Response;
import com.board.boardsite.dto.response.article.ArticleResponse;
import com.board.boardsite.dto.response.article.ArticleWithCommentsResponse;
import com.board.boardsite.dto.security.TripUserPrincipal;
import com.board.boardsite.service.aritcle.ArticleService;
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
@Api(tags ={"게시판 정보 조회 Controller"})
@RestController
@RequestMapping("/api/trip/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;


    @GetMapping("")
    @ApiOperation(value = "게시판 조회", notes = "게시판 목록을 조회 한다.")
    public Response<Page<ArticleResponse>> articles(@RequestParam(required = false) SearchType searchType,
                                              @RequestParam(required = false) String searchKeyWord,
                                              @PageableDefault(size=10,sort="createdAt",direction= Sort.Direction.DESC)Pageable pageable) {

        Page<ArticleResponse> articles = articleService.articleSearchList(searchType,searchKeyWord,pageable).map(ArticleResponse::from);
        return Response.success(articles);
    }

    @GetMapping("/{articleId}")
    @ApiOperation(value = "게시판 상세 조회", notes = "게시판 상세 조회 한다.")
    public Response<ArticleWithCommentsResponse> articleDetail(@PathVariable Long articleId,
                                                               @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
        var articleDetail = ArticleWithCommentsResponse.from(articleService.getArticleWithComment(articleId),tripUserPrincipal);
        return Response.success(articleDetail);
    }

    @GetMapping("/valid/{articleId}")
    @ApiOperation(value = "게시판 상세 유효성 조회", notes = "게시판 목록의 사용자 정보를 유효성 검사를 한다.")
    public Response<ArticleResponse> articleValidDetail(@PathVariable Long articleId,
                                                               @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {

        var articleValidDetail = ArticleResponse.from(articleService.articleValidDetail(articleId , tripUserPrincipal.id()));

        return Response.success(articleValidDetail);
    }

    @PostMapping("/new-article")
    @ApiOperation(value = "게시판 생성", notes = "게시판을 생성한다.")
    public Response<Boolean> saveArticle(@Valid @RequestBody ArticleRequest articleRequest ,
                                         @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal) {
         articleService.saveArticle(articleRequest.toDto(tripUserPrincipal.toDto()));
    return Response.success(true);
    }

    @PostMapping("/{articleId}/form")
    @ApiOperation(value = "게시판 수정", notes = "게시판을 수정한다.")
    public Response<Boolean> updateArticle(@PathVariable Long articleId ,
                                           @Valid @RequestBody ArticleRequest articleRequest ,
                                           @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {

        articleService.updateArticle(articleId,articleRequest.toDto(tripUserPrincipal.toDto()));
        return Response.success(true);
    }

    @PutMapping("/{articleId}/delete")
    @ApiOperation(value = "게시판 삭제", notes = "게시판을 삭제한다.")
    public Response<Boolean> deleteArticle(@PathVariable Long articleId ,
                                           @AuthenticationPrincipal TripUserPrincipal tripUserPrincipal)
    {
        articleService.deleteArticle(articleId , tripUserPrincipal.id());
        return Response.success(true);
    }


}
