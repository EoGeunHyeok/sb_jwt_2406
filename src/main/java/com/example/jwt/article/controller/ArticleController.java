package com.example.jwt.article.controller;

import com.example.jwt.article.entity.Article;
import com.example.jwt.article.service.ArticleService;
import com.example.jwt.global.rsData.RsData;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/articles")
public class ArticleController {
    private final ArticleService articleService;

    @AllArgsConstructor
    @Getter
    public static class ArticleRespose {
        private final List<Article> articles;
    }
    @GetMapping(value = "")
    @Operation(summary = "게시물들")
    public RsData<ArticleRespose> articles(){
        List<Article> articles = articleService.findAll();

        return RsData.of(
                "S-1",
                "성공",
                new ArticleRespose(articles)
        );
    }
}
