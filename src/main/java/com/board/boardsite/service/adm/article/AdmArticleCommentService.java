package com.board.boardsite.service.adm.article;

import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.article.ArticleComment;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.article.ArticleCommentDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.adm.article.AdmArticleCommentRepository;
import com.board.boardsite.repository.adm.article.AdmArticleRepository;
import com.board.boardsite.repository.article.ArticleCommentRepository;
import com.board.boardsite.repository.article.ArticleRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdmArticleCommentService {

    private final AdmArticleCommentRepository admArticleCommentRepository;

    private final AdmArticleRepository admArticleRepository;

    private final TripUserRepository tripUserRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComments(Long articleId) {
        return admArticleCommentRepository.findByArticle_IdAndDeleted(articleId,false)
                .stream().map(ArticleCommentDto::from).toList();
    }

    @Transactional(readOnly = true)
    public Page<ArticleCommentDto> searchArticleCommentsPage(Long articleId,Pageable pageable) {
        return admArticleCommentRepository.findByArticle_IdAndDeleted(articleId,false,pageable).map(ArticleCommentDto::from);
    }

    @Transactional
    public void saveArticleComment(ArticleCommentDto dto) {
        try {
            Article article = admArticleRepository.findById(dto.articleId()).orElseThrow(()->new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND));
            TripUser tripUser = tripUserRepository.getReferenceById(dto.tripUser().id());
            admArticleCommentRepository.save(dto.toEntity(article, tripUser));
        } catch (Exception e) {
            throw new BoardSiteException(ErrorCode.EMAIL_NOT_FOUND);
        }
    }

    @Transactional
    public void updateArticleComment(Long articleCommentId , ArticleCommentDto dto){

        ArticleComment articleComment = admArticleCommentRepository.findById(articleCommentId).orElseThrow(()->new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND));
        if (dto.content() != null) {
            articleComment.setContent(dto.content());
        }

    }

    @Transactional
    public void deleteArticleComment(Long articleCommentId) {
            ArticleComment articleComment = admArticleCommentRepository.findById(articleCommentId).orElseThrow(()->new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND));
            articleComment.setDeleted(true);
    }
}
