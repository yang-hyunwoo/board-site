package com.board.boardsite.service.aritcle;

import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.constant.SearchType;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.article.ArticleDto;
import com.board.boardsite.dto.article.ArticleWithCommentsDto;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.repository.article.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("게시글 페이지")
@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @MockBean
    private ArticleRepository articleRepository;


    @DisplayName("[GET][service] 검색어 없이 게시글 리스트 조회" )
    @Test
    void givenNoSearchParameters_whenSearchArticles_thenReturnArticleList() {
        Pageable pageable = Pageable.ofSize(10);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());
        Page<ArticleDto> articles = articleService.articleSearchList(null,null,pageable);
        assertThat(articles).isEmpty();

        then(articleRepository).should().findAll(pageable);

    }

    @DisplayName("[GET][service] 검색어를 입력하여 게시글 리스트 조회")
    @Test
    void givenSearchParameters_whenSearchArticles_thenReturnArticleList() {
        // Given
        Pageable pageable = Pageable.ofSize(10);
        SearchType searchType = SearchType.TITLE;
        String searchKeyWord = "title";
        given(articleRepository.findByTitleContaining(searchKeyWord,pageable)).willReturn(Page.empty());

        // When
        var articleDto = articleService.articleSearchList(searchType,searchKeyWord,pageable);

        // Then
        assertThat(articleDto).isEmpty();
        then(articleRepository).should().findByTitleContaining(searchKeyWord,pageable);
    }

    @DisplayName("[GET][service] 게시글 상세 조회")
    @Test
    void givenArticleId_whenArticle_thenReturnArticleAndComment() {

        // Given
        Long articleId = 1L;
        Article article = createArticle();
        given(articleRepository.findById(articleId)).willReturn(Optional.of(article));

        // When & Then
        var dto = articleService.getArticleWithComment(articleId);
        assertThat(dto)
                .hasFieldOrPropertyWithValue("title",article.getTitle())
                .hasFieldOrPropertyWithValue("content",article.getContent());
        then(articleRepository).should().findById(articleId);


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