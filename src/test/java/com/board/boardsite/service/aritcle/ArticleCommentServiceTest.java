package com.board.boardsite.service.aritcle;

import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.article.ArticleComment;
import com.board.boardsite.domain.constant.Gender;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.article.ArticleCommentDto;
import com.board.boardsite.dto.user.TripUserDto;
import com.board.boardsite.repository.article.ArticleCommentRepository;
import com.board.boardsite.repository.article.ArticleRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("게시글 댓글")
@SpringBootTest
public class ArticleCommentServiceTest {

    @Autowired
    private ArticleCommentService articleCommentService;

    @MockBean
    private ArticleCommentRepository articleCommentRepository;

    @MockBean
    private ArticleRepository articleRepository;

    @MockBean
    private TripUserRepository tripUserRepository;



    @DisplayName("게시글 ID로 조회시 게시글 댓글 리스트 반환")
    @Test
    void giveArticleId_whenRequest_thenReturnArticleList() {
        Long articleId = 1L;
        ArticleComment articleComment = createArticleComment("test");
        given(articleCommentRepository.findByArticle_Id(articleId)).willReturn(List.of(articleComment));

        List<ArticleCommentDto> articleCommentDtos = articleCommentService.searchArticleComments(articleId);

        assertThat(articleCommentDtos)
                .hasSize(1)
                .first().hasFieldOrPropertyWithValue("content",articleComment.getContent());
        then(articleCommentRepository).should().findByArticle_Id(articleId);

    }

    @DisplayName("[POST][service] 댓글을 입력 하면 댓글 저장")
    @Test
    void giveArticleComment_whenRequestSavingArticleComment_thenReturnArticleComment() {
        // Given
        ArticleCommentDto dto = createArticleCommentDto("신규 댓글");
        given(articleRepository.getReferenceById(dto.articleId())).willReturn(createArticle());
        given(tripUserRepository.getReferenceById(dto.tripUser().id())).willReturn(createTripUser());
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);
        // When
        articleCommentService.saveArticleComment(dto);
        // Then
        then(articleRepository).should().getReferenceById(dto.articleId());
        then(tripUserRepository).should().getReferenceById(dto.tripUser().id());
        then(articleCommentRepository).should().save(any(ArticleComment.class));

    }
    @Disabled
    @DisplayName("[PUT][service] 작성한 댓글을 수정 현재 서비스단에서만 에러가남 ")
    @Test
    void giveArticleComment_whenRequestUpdateArticleComment_thenReturnArticleComment() {
        String oldContent ="1";
        String newContent = "2";
        ArticleComment articleComment = createArticleComment(oldContent);
        ArticleCommentDto articleCommentDto = createArticleCommentDto(newContent);
        given(articleCommentRepository.getReferenceById(articleCommentDto.id())).willReturn(articleComment);

        articleCommentService.updateArticleComment(articleComment.getId(),articleCommentDto);

        assertThat(articleComment.getContent())
                .isEqualTo(newContent);
        then(articleCommentRepository).should().getReferenceById(articleCommentDto.id());
    }

    @Disabled
    @DisplayName("댓글 ID를 입력하면, 댓글을 삭제한다. 현재 서비스단에서만 에러가남")
    @Test
    void givenArticleCommentId_whenDeletingArticleComment_thenDeletesArticleComment() {
        // Given
        Long articleCommentId = 1L;
        Long id = 1L;
        willDoNothing().given(articleCommentRepository).deleteByIdAndTripUser_Id(articleCommentId ,id);
        // When
        articleCommentService.deleteArticleComment(articleCommentId , id);
        then(articleCommentRepository).should().deleteByIdAndTripUser_Id(articleCommentId , id);
    }

    private ArticleComment createArticleComment(String content) {
        return ArticleComment.of(
                Article.of( "title", "content",false,createTripUser()),
                content,
                false,
                createTripUser()
        );
    }

    private TripUser createTripUser() {
        return TripUser.of(
                1L,
                "gus",
                "gus",
                "gus5162@naver.com",
                "test",
                "010",
                0,
                Gender.M,
                false,
                false
        );
    }

    private Article createArticle() {
        return Article.of(
                "title",
                "content",
                false,
                createTripUser()
        );
    }

    private ArticleCommentDto createArticleCommentDto(String content) {
        return ArticleCommentDto.of(
                1L,
                1L,
                createTripUserDto(),
                content,
                false,
                null,
                null,
                null,
                null
        );
    }

    private TripUserDto createTripUserDto() {
        return TripUserDto.of(
               1L,
                "gus5162@naver.com",
                "gus",
                "gus",
                "0102",
                0,
                false,
                "test",
                Gender.M,
                false,
                LocalDateTime.now(),
                null,
                LocalDateTime.now(),
                null

        );
    }

}
