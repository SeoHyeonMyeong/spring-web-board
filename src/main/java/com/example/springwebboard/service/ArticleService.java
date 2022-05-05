package com.example.springwebboard.service;

import com.example.springwebboard.dto.ArticleForm;
import com.example.springwebboard.entity.Article;
import com.example.springwebboard.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired // DI
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article detail(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if (article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(ArticleForm dto, Long id) {
        Article article = dto.toEntity();
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null || id != target.getId()) {
            return null;
        }
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Boolean delete(Long id){
        Article target = articleRepository.findById(id).orElse(null);
        if (target == null){
            return false;
        }
        articleRepository.delete(target);
        return true;
    }

}
