package com.board.boardsite.controller;

import com.board.boardsite.controller.articleController.ArticleController;
import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.constant.SearchType;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.article.ArticleDto;
import com.board.boardsite.dto.article.ArticleWithCommentsDto;
import com.board.boardsite.dto.request.article.ArticleRequest;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.service.aritcle.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("컨트롤러 - 게시글")
@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {
    @Autowired
    private final MockMvc mvc;

    @MockBean
    private ArticleService articleService;

    @Autowired
    private ObjectMapper objectMapper;

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[GET] 게시글 리스트 조회 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticlesList_thenReturnsArticleList() throws Exception {
        // Given

        given(articleService.articleSearchList(eq(null),eq(null),any(Pageable.class))).willReturn(Page.empty());

        // When & Then
        mvc.perform(get("/api/trip/articles")
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

        then(articleService).should().articleSearchList(eq(null), eq(null), any(Pageable.class));
    }

    @DisplayName("[GET] 게시글 검색어 입력 리스트 조회")
    @Test
    public void givenSearchParameters_whenSearchArticles_thenReturnArticleList() throws Exception {
        SearchType searchType = SearchType.TITLE;
        String searchKeyWord = "title";

        given(articleService.articleSearchList(eq(searchType),eq(searchKeyWord),any(Pageable.class))).willReturn(Page.empty());

        mvc.perform(get("/api/trip/articles")
                .queryParam("searchType", String.valueOf(searchType))
                .queryParam("searchKeyWord",searchKeyWord)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk());

    }

    @DisplayName("[view][GET] 게시글 리스트 상세 페이지 조회(댓글 포함) - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticleDetail_thenReturnsArticleDetail() throws Exception {
        // Given
        Long articleId = 1L;
        given(articleService.getArticleWithComment(articleId)).willReturn(createArticleWithCommentsDto());

        // When & Then
        mvc.perform(get("/api/trip/articles/"+articleId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
        then(articleService).should().getArticleWithComment(articleId);
    }


    private Article createArticle() {
        Article article = Article.of(
                "title",
                "content",
                false,
                createTripUser2()
        );

        return article;
    }
    private TripUserDto createTripUser() {
        return TripUserDto.of(
                "gus@naver.com",
                "aaa",
                "qqll",
                "test",
                Gender.M
        );
    }

    private TripUser createTripUser2() {
        return TripUser.of(
                "aaa",
                "aaa",
                "gus@naver.com",
                "test",
                0,
                Gender.M,
                false,
                false
        );
    }

    private ArticleDto createArticleDto() {
        return ArticleDto.of(
                createTripUser(),
                "title",
                "content"
        );
    }

    private ArticleWithCommentsDto createArticleWithCommentsDto() {
        return ArticleWithCommentsDto.of(
                1L,
                createTripUser(),
                Set.of(),
                "title",
                "content",
                LocalDateTime.now(),
                "11",
                LocalDateTime.now(),
                "22"
        );
    }



}