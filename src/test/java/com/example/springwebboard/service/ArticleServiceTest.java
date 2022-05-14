package com.example.springwebboard.service;

import com.example.springwebboard.dto.ArticleForm;
import com.example.springwebboard.entity.Article;
import com.example.springwebboard.repository.ArticleRepository;
import org.h2.security.auth.H2AuthConfig;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource("classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ArticleServiceTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    public ArticleService articleService;

    public List<Article> articles;
    public List<String> data = Arrays.asList("aaaaa","bbbbb","ccccc","ddddd");
    public Long nextId;

    @BeforeAll
    void setupData() {
        articles = data.stream()
                .map(item -> new ArticleForm(null, item, item))
                .map(dto -> articleService.create(dto))
                .collect(Collectors.toList());
        nextId = articles.get(articles.size() - 1).getId() + 1L;
    }

    @AfterAll
    void deleteData() {
        articles.forEach(article -> articleService.delete(article.getId()));
    }

    @Test
    @DisplayName("게시글 리스트")
    void index() {
        // given
        List<Article> expected = articles;

        // when
        List<Article> articlelist = articleService.index();
        List<Article> articles = articlelist.stream()
                .skip(articlelist.size() - expected.size())
                .collect(Collectors.toList());

        // then
        assertEquals(expected.toString(), articles.toString());
        System.out.println(expected.toString());
        System.out.println(articles.toString());
    }

    @Test
    @DisplayName("게시글 상세")
    void detail() {
        Long id = 1L;
        // 예상
        Article expected = new Article(id, "aaaaa", "1111");

        // 실제
        Article article = articleService.detail(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @DisplayName("게시글 상세___없는 ID")
    void detailWrongId() {
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
    @DisplayName("게시글 생성")
    void create() {
        // 예상
        Long id = nextId;
        String title = "eeeee";
        String content = "55555";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(nextId, title, content);

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    @DisplayName("게시글 생성___없는_ID")
    void createWrongArticle() {
        // 예상
        Long id = -1L;
        String title = "eeeee";
        String content = "55555";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;

        // 실제
        Article article = articleService.create(dto);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    @DisplayName("게시글 수정")
    void update() {
        // 예상
        Long id = articles.get(0).getId();
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
    @DisplayName("게시글 수정___없는 ID")
    void update_failed___없는_id_요청() {
        // 예상
        Long id = -1L;
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
    @DisplayName("게시글 삭제")
    void delete() {
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
    @DisplayName("게시글 삭제___없는 ID")
    void deleteWrongId() {
        // 예상
        Long id = -1L;

        // 실제
        Boolean success = articleService.delete(id);

        // 비교
        assertEquals(success, false);
    }
}