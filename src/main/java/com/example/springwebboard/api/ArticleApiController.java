package com.example.springwebboard.api;

import com.example.springwebboard.dto.ArticleForm;
import com.example.springwebboard.entity.Article;
import com.example.springwebboard.repository.ArticleRepository;
import com.example.springwebboard.service.ArticleService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArticleApiController {

    @Autowired
    private ArticleService articleService;

    // GET List
    @GetMapping("/api/articles")
    public List<Article> index() {
        return articleService.index();
    }

    // GET One
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<Article> detail(@PathVariable Long id) {
        Article article = articleService.detail(id);
        return (article != null) ?
                ResponseEntity.status(HttpStatus.OK).body(article):
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // POST
    @PostMapping("/api/articles")
    public ResponseEntity<Article> create(@RequestBody ArticleForm dto) {
        Article created = articleService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.CREATED).body(created):
                ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@RequestBody ArticleForm dto,
                          @PathVariable Long id) {
        Article updated = articleService.update(dto, id);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        return (articleService.delete(id)) ?
                ResponseEntity.status(HttpStatus.OK).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
