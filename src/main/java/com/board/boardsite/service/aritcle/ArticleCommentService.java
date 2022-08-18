package com.board.boardsite.service.aritcle;

import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.article.ArticleComment;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.article.ArticleCommentDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.article.ArticleCommentRepository;
import com.board.boardsite.repository.article.ArticleRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleCommentService {

    private final ArticleCommentRepository articleCommentRepository;

    private final ArticleRepository articleRepository;

    private final TripUserRepository tripUserRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComments(Long articleId) {
        return articleCommentRepository.findByArticle_Id(articleId)
                .stream().map(ArticleCommentDto::from).toList();
    }

    @Transactional
    public void saveArticleComment(ArticleCommentDto dto) {
        try {
            Article article = articleRepository.getReferenceById(dto.articleId());
            TripUser tripUser = tripUserRepository.getReferenceById(dto.tripUser().id());
            articleCommentRepository.save(dto.toEntity(article, tripUser));
        } catch (Exception e) {
            throw new BoardSiteException(ErrorCode.EMAIL_NOT_FOUND);
        }
    }

//    @Transactional
    public void updateArticleComment(Long articleCommentId , ArticleCommentDto dto){

        ArticleComment articleComment = articleCommentRepository.getReferenceById(articleCommentId);
        System.out.println(articleComment.getTripUser().getName());
        System.out.println(dto.tripUser());
        if(articleComment.getTripUser().equals(dto.tripUser())) {
            if (dto.content() != null) {
                articleComment.setContent(dto.content());
                }
        } else {
            throw  new BoardSiteException(ErrorCode.ARTICLE_COMMENT_UNAUTHORIZED);
        }

    }

    public void deleteArticleComment(Long articleCommentId , Long userId) {
            ArticleComment articleComment = articleCommentRepository.getReferenceById(articleCommentId);
            TripUser tripUser = tripUserRepository.getReferenceById(userId);
            if (articleComment.getTripUser().equals(tripUser)) {
                articleComment.setDeleted(true);
            } else{
                throw new BoardSiteException(ErrorCode.ARTICLE_COMMENT_UNAUTHORIZED);
            }
    }
}
