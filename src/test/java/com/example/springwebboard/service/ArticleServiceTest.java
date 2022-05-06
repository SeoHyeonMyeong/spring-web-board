package com.example.springwebboard.service;

import com.example.springwebboard.dto.ArticleForm;
import com.example.springwebboard.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        // 예상
        Article a = new Article(1L, "aaaaa", "1111");
        Article b = new Article(2L, "bbbbb", "2222");
        Article c = new Article(3L, "ccccc", "3333");
        Article d = new Article(4L, "ddddd", "4444");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c, d));

        // 실제
        List<Article> articles = articleService.index();

        // 비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void detail_success() {
        Long id = 1L;
        // 예상
        Article expected = new Article(id, "aaaaa", "1111");

        // 실제
        Article article = articleService.detail(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void detail_failed___없는_id_요청() {
        Long id = -1L;
        // 예상
        Article expected = null;

        // 실제
        Article article = articleService.detail(id);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_success() {
        // 예상
        String title = "";
        String content = "";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(5L, title, content);

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_failed___id가_포함된_dto_입력() {
        // 예상
        String title = "eeeee";
        String content = "5555";
        ArticleForm dto = new ArticleForm(5L, title, content);
        Article expected = null;

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void update_success() {
        // 예상
        Long id = 1L;
        String title = "AAAAA";
        String content = "1111155555";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(id, title, content);

        // 실제
        Article article = articleService.update(dto, id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void update_failed___없는_id_요청() {
        // 예상
        Long id = 5L;
        String title = "AAAAA";
        String content = "1111155555";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = null;

        // 실제
        Article article = articleService.update(dto, id);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void delete_success() {
        // 예상
        Long id = 1L;
        Article expected = null;

        // 실제
        Boolean success = articleService.delete(id);
        Article article = articleService.detail(id);

        // 비교
        assertEquals(expected, article);
        assertEquals(success, true);
    }

    @Test
    @Transactional
    void delete_failed__없는_id_요청() {
        // 예상
        Long id = 5L;

        // 실제
        Boolean success = articleService.delete(id);

        // 비교
        assertEquals(success, false);
    }
}