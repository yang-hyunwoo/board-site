package com.board.boardsite.controller;


import com.board.boardsite.domain.Article;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    @GetMapping
    public Article articles(Article article) {
        return article;
    }

}
