package com.board.boardsite.repository.article;

import com.board.boardsite.domain.article.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {

    List<ArticleComment> findByArticle_IdAndDeleted(Long articleId,boolean deleted);

    Optional<ArticleComment> findByIdAndDeleted(Long articleId , boolean deleted);
    void deleteByIdAndTripUser_Id(Long articleCommentId , Long id);
}
