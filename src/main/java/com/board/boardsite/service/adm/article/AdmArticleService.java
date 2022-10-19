package com.board.boardsite.service.adm.article;


import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.constant.SearchType;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.article.ArticleDto;
import com.board.boardsite.dto.article.ArticleWithCommentsDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.adm.article.AdmArticleRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdmArticleService {

    private final AdmArticleRepository admArticleRepository;

    private final TripUserRepository tripUserRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> articleSearchList(SearchType searchType , String searchKeyWord , Pageable pageable) {

        if (searchKeyWord == null || searchKeyWord.isBlank()) {
            return admArticleRepository.findAll(pageable).map(ArticleDto::from);
        }
        return switch (searchType) {
            case TITLE -> admArticleRepository.findByTitleContaining(searchKeyWord, pageable).map(ArticleDto::from);
            case NICKNAME -> admArticleRepository.findByTripUser_NickNameContaining(searchKeyWord, pageable).map(ArticleDto::from);
        };
    }

    //수정
    @Transactional
    public ArticleWithCommentsDto getArticleWithComment(Long articleId) {
        var articleDetail = admArticleRepository.findById(articleId).orElseThrow(()->new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND));
        Optional<Article> optionalArticle = Optional.of(articleDetail);
        return optionalArticle
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND,"게시글이 없습니다."));
    }

    @Transactional
    public ArticleDto articleValidDetail(Long articleId , Long userId){
        Article article = admArticleRepository.findById(articleId).orElseThrow(()->new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND));
            return Optional.of(article).map(ArticleDto::from).orElseThrow(()->new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND));
    }

    @Transactional
    public void saveArticle(ArticleDto dto){
        TripUser tripUser = tripUserRepository.getReferenceById(dto.tripUser().id()) ;
        admArticleRepository.save(dto.toEntity(tripUser));
    }

    @Transactional
    public void updateArticle(Long articleId, ArticleDto dto) {
        try {
            Article article = admArticleRepository.findByIdAndDeleted(articleId,false).orElseThrow(()->new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND));
            TripUser tripUser = tripUserRepository.getReferenceById(dto.tripUser().id());
            if (article.getTripUser().getId().equals(tripUser.getId())) {
                if (dto.title() != null) { article.setTitle(dto.title()); }
                if (dto.content() != null) { article.setContent(dto.content()); }
            } else {
                throw new BoardSiteException(ErrorCode.ARTICLE_COMMENT_UNAUTHORIZED);
            }
        } catch (Exception e) {
            new BoardSiteException(ErrorCode.ARTICLE_UPDATE_FAIL);
        }
    }


    @Transactional
    public void deleteArticle(Long articleId){
            Article article = admArticleRepository.findByIdAndDeleted(articleId,false).orElseThrow(()->new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND));
            article.setDeleted(true);
    }

    @Transactional
    public void reDeleteArticle(Long articleId){
        Article article = admArticleRepository.findByIdAndDeleted(articleId,true).orElseThrow(()->new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND));
        article.setDeleted(false);
    }

}
