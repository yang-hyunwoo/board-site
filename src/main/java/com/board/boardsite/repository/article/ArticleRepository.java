package com.board.boardsite.repository.article;

import com.board.boardsite.domain.article.Article;
import com.board.boardsite.domain.article.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,     //모든 검색 정확한 검색에서 사용
        QuerydslBinderCustomizer<QArticle>      //선택적 검색 like 검색에서 사용
        {

            Page<Article> findByTitleContaining(String title , Pageable pageable);

            Page<Article> findByTripUser_NickNameContaining(String nickname , Pageable pageable);

            @Override
            default void customize(QuerydslBindings bindings, QArticle root){
                bindings.excludeUnlistedProperties(true);
                bindings.including(root.title,root.createdAt,root.createdBy);
                bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
                bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
                bindings.bind(root.createdAt).first(DateTimeExpression::eq);
            }
        }
