package com.board.boardsite.repository;

import com.board.boardsite.domain.Article;
import com.board.boardsite.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article , Long>,
        QuerydslPredicateExecutor<Article>,     //모든 검색 정확한 검색에서 사용
        QuerydslBinderCustomizer<QArticle>      //선택적 검색 like 검색에서 사용
        {
            @Override
            default void customize(QuerydslBindings bindings, QArticle root){
                bindings.excludeUnlistedProperties(true);
                bindings.including(root.title,root.createdAt,root.createdBy);
                bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
                bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
                bindings.bind(root.createdAt).first(DateTimeExpression::eq);
            }
        }
