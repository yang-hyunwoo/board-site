package com.board.boardsite.service.aritcle;

import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.constant.SearchType;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.article.ArticleDto;
import com.board.boardsite.dto.article.ArticleWithCommentsDto;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.article.ArticleRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;

@DisplayName("게시글 페이지")
@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @MockBean
    private ArticleRepository articleRepository;

    @MockBean
    private TripUserRepository tripUserRepository;

    @MockBean
    private BCryptPasswordEncoder encoder;

    @DisplayName("[GET][service] 검색어 없이 게시글 리스트 조회")
    @Test
    void givenNoSearchParameters_whenSearchArticles_thenReturnArticleList() {
        Pageable pageable = Pageable.ofSize(10);
        given(articleRepository.findAll(pageable)).willReturn(Page.empty());
        Page<ArticleDto> articles = articleService.articleSearchList(null, null, pageable);
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
        given(articleRepository.findByTitleContaining(searchKeyWord, pageable)).willReturn(Page.empty());

        // When
        var articleDto = articleService.articleSearchList(searchType, searchKeyWord, pageable);

        // Then
        assertThat(articleDto).isEmpty();
        then(articleRepository).should().findByTitleContaining(searchKeyWord, pageable);
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
                .hasFieldOrPropertyWithValue("title", article.getTitle())
                .hasFieldOrPropertyWithValue("content", article.getContent());
        then(articleRepository).should().findById(articleId);

    }

    @DisplayName("[GET][service] 게시물 상세 조회시 게시글이 없는 경우")
    @Test
    void givenErrorArticleId_whenRequesting_thenReturnException() {
        Long articleId = 100L;

        BoardSiteException e = Assertions.assertThrows(BoardSiteException.class, () -> articleService.getArticleWithComment(articleId));
        Assertions.assertEquals(ErrorCode.ARTICLE_NOT_FOUND, e.getErrorCode());

    }

    @DisplayName("[POST][service] 게시글 신규 등록 시  - 정상 ")
    @Test
    void givenArticle_whenSavingArticle_thenReturnSaveArticle() {
        // Given
        ArticleDto dto = createArticleDto();
        given(tripUserRepository.getReferenceById(dto.tripUser().id())).willReturn(createTripUser2());
        given(articleRepository.save(any(Article.class))).willReturn(createArticle());
        // When
        articleService.saveArticle(dto);
        // Then
        then(tripUserRepository).should().getReferenceById(dto.tripUser().id());
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("[POST][service] 게시글 수정 시 - 정상")
    @Test
    void givenArticle_whenUpdateArticle_thenReturnSaveArticle() {
        ArticleDto dto = createArticleDto("new title", "new content");
        Article article = createArticle();

        // Given
        given(articleRepository.getReferenceById(dto.id())).willReturn(article);
        TripUser tripUser = dto.tripUser().toEntity(encoder.encode(dto.tripUser().password()));
        given(tripUserRepository.getReferenceById(dto.tripUser().id())).willReturn(tripUser);
        // When
        articleService.updateArticle(dto.id(), dto);

        // Then
        assertThat(article)
                .hasFieldOrPropertyWithValue("title", dto.title())
                .hasFieldOrPropertyWithValue("content", dto.content());
        then(articleRepository).should().getReferenceById(dto.id());
        then(tripUserRepository).should().getReferenceById(dto.tripUser().id());
    }

    @DisplayName("[POST][service] 게시글 삭제 시- 정상")
    @Test
    void givenArticleId_whenDeleteArticle_thenReturnDeleteArticle() {
        // Given
        Long articleId = 1L;
        Long id = 51L;
        willDoNothing().given(articleRepository).deleteByIdAndTripUser_Id(articleId ,id);

        // When
        articleService.deleteArticle(articleId , id);

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
                "gus5162@naver.com",
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
    private ArticleDto createArticleDto(String title, String content) {
        return ArticleDto.of(
                1L,
                createTripUser(),
                "title",
                "content",
                false,
                null,
                null,
                null,
                null);
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