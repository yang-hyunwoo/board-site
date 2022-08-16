package com.board.boardsite.service.aritcle;


import com.board.boardsite.domain.constant.SearchType;
import com.board.boardsite.dto.article.ArticleDto;
import com.board.boardsite.dto.article.ArticleWithCommentsDto;
import com.board.boardsite.exception.BoardSiteException;
import com.board.boardsite.exception.ErrorCode;
import com.board.boardsite.repository.article.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

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
                .orElseThrow(() -> new BoardSiteException(ErrorCode.EMAIL_NOT_FOUND));
    }
}
