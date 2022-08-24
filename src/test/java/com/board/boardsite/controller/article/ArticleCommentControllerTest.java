package com.board.boardsite.controller.article;

import com.board.boardsite.config.TestSecurityConfig;
import com.board.boardsite.dto.article.ArticleCommentDto;
import com.board.boardsite.dto.request.article.ArticleCommentRequest;
import com.board.boardsite.service.aritcle.ArticleCommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("게시글 댓글")
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestSecurityConfig.class)
class ArticleCommentControllerTest {

    @Autowired
    private final MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private  ArticleCommentService articleCommentService;

    public ArticleCommentControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[POST][controller] 게시글 댓글 저장")
    @Test
    @WithUserDetails(value = "gusdnTest", setupBefore = TestExecutionEvent.TEST_EXECUTION )
    void giveArticleComment_whenRequestSavingArticleComment_thenReturnArticleComment() throws Exception{

        // Given
        ArticleCommentRequest articleCommentRequest = ArticleCommentRequest.of(1L,"aaaa");
        willDoNothing().given(articleCommentService).saveArticleComment(any(ArticleCommentDto.class));

        // When & Then

        mvc.perform(post("/api/trip/articles/comment/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(articleCommentRequest)))
                .andDo(print())
                .andExpect(status().isOk());
        then(articleCommentService).should().saveArticleComment(any(ArticleCommentDto.class));
    }

    @DisplayName("[POST][controller] 게시글 댓글 수정")
    @Test
    @WithUserDetails(value = "gusdnTest", setupBefore = TestExecutionEvent.TEST_EXECUTION )
    void giveArticleComment_whenRequestUpdateArticleComment_thenReturnArticleComment() throws Exception{
        // Given
        long articleCommentId = 1L;
        ArticleCommentRequest articleCommentRequest = ArticleCommentRequest.of(articleCommentId,"aaaa");
        willDoNothing().given(articleCommentService).updateArticleComment(eq(articleCommentId),any(ArticleCommentDto.class));

        // When & Then

        mvc.perform(put("/api/trip/articles/comment/"+articleCommentId+"/form")
                .content(objectMapper.writeValueAsBytes(articleCommentRequest))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        then(articleCommentService).should().updateArticleComment(eq(articleCommentId),any(ArticleCommentDto.class));
    }

    @DisplayName("[POST][controller] 게시글 댓글 삭제")
    @Test
    @WithUserDetails(value = "aaaaaaaaaa", setupBefore = TestExecutionEvent.TEST_EXECUTION )
    void giveArticleCommentId_whenRequestDeleteArticleComment_thenReturnArticleComment() throws Exception {
        long articleCommentId = 1L;
        long id = 51L;
        willDoNothing().given(articleCommentService).deleteArticleComment(articleCommentId, id);

        mvc.perform(put("/api/trip/articles/comment/" + articleCommentId + "/delete")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }
}