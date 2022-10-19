package com.board.boardsite.service.aritcle;


import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.constant.SearchType;
import com.board.boardsite.domain.user.TripUser;
import com.board.boardsite.dto.article.ArticleDto;
import com.board.boardsite.dto.article.ArticleWithCommentsDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.article.ArticleRepository;
import com.board.boardsite.repository.user.TripUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final TripUserRepository tripUserRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> articleSearchList(SearchType searchType , String searchKeyWord , Pageable pageable) {

        if (searchKeyWord == null || searchKeyWord.isBlank()) {
            return articleRepository.findAllByDeleted(pageable,false).map(ArticleDto::from);
        }
        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContainingAndDeleted(searchKeyWord, pageable,false).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByTripUser_NickNameContainingAndDeleted(searchKeyWord, pageable , false).map(ArticleDto::from);
        };
    }

    //수정
    @Transactional
    public ArticleWithCommentsDto getArticleWithComment(Long articleId) {
        var articleDetail = articleRepository.findByIdAndDeleted(articleId,false).orElseThrow(()->new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND));
        articleDetail.readCountPlus(articleDetail.getReadCount());
        Optional<Article> optionalArticle = Optional.of(articleDetail);
        return optionalArticle
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND,"게시글이 없습니다."));
    }

    @Transactional
    public ArticleDto articleValidDetail(Long articleId , Long userId){
        Article article = articleRepository.findByIdAndDeleted(articleId,false).orElseThrow(()->new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND));
        if(article.getTripUser().getId().equals(userId)){
            return Optional.of(article).map(ArticleDto::from).orElseThrow(()->new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND));
        }
        throw  new BoardSiteException(ErrorCode.ARTICLE_UNAUTHORIZED);
    }

    @Transactional
    public void saveArticle(ArticleDto dto){
        TripUser tripUser = tripUserRepository.getReferenceById(dto.tripUser().id()) ;
        articleRepository.save(dto.toEntity(tripUser));
    }

    @Transactional
    public void updateArticle(Long articleId, ArticleDto dto) {
        try {
            Article article = articleRepository.findByIdAndDeleted(articleId,false).orElseThrow(()->new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND));
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
    public void deleteArticle(Long articleId ,Long userId){
        try {
            Article article = articleRepository.findByIdAndDeleted(articleId,false).orElseThrow(()->new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND));
            TripUser tripUser = tripUserRepository.getReferenceById(userId);
            if (article.getTripUser().getId().equals(tripUser.getId())) {
                article.setDeleted(true);
            } else{
                throw new BoardSiteException(ErrorCode.ARTICLE_COMMENT_UNAUTHORIZED);
            }
        } catch (Exception e) {
            new BoardSiteException(ErrorCode.ARTICLE_DELETE_FAIL);
        }
    }
}
