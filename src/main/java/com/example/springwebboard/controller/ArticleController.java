package com.example.springwebboard.controller;

import com.example.springwebboard.dto.ArticleForm;
import com.example.springwebboard.entity.Article;
import com.example.springwebboard.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {

        // 1. Dto 를 Entity로 변환
        Article article = form.toEntity();

        // 2. Repository에게 Entity를 DB에 저장하게 함!
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {

        // 1. id로 데이터를 가져온다.
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지 리턴
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {

        // 1. 모든 아티클을 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();

        // 2. 가져온 아티클을 모델에 등록
        model.addAttribute("articleList", articleEntityList);

        // 3. 뷰 페이지 설정
        return "articles/index";
    }
}
