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

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final TripUserRepository tripUserRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> articleSearchList(SearchType searchType , String searchKeyWord , Pageable pageable) {

        if (searchKeyWord == null || searchKeyWord.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }
        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyWord, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByTripUser_NickNameContaining(searchKeyWord, pageable).map(ArticleDto::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentsDto getArticleWithComment(Long articleId) {

        return articleRepository.findById(articleId)
                .map(ArticleWithCommentsDto::from)
                .orElseThrow(() -> new BoardSiteException(ErrorCode.ARTICLE_NOT_FOUND,"게시글이 없습니다."));
    }

    @Transactional
    public void saveArticle(ArticleDto dto){
        System.out.println(dto.tripUser().id());
        TripUser tripUser = tripUserRepository.getReferenceById(dto.tripUser().id()) ;
        articleRepository.save(dto.toEntity(tripUser));
    }

    @Transactional
    public void updateArticle(Long articleId, ArticleDto dto) {
        try {
            Article article = articleRepository.getReferenceById(articleId);
            TripUser tripUser = tripUserRepository.getReferenceById(dto.tripUser().id());

            if (article.getTripUser().equals(tripUser)) {
                if (dto.title() != null) { article.setTitle(dto.title()); }
                if (dto.content() != null) { article.setContent(dto.content()); }
            }
        } catch (Exception e) {
            new BoardSiteException(ErrorCode.ARTICLE_UPDATE_FAIL);
        }
    }
}
