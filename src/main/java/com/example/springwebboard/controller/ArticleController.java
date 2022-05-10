package com.example.springwebboard.controller;

import com.example.springwebboard.dto.ArticleForm;
import com.example.springwebboard.dto.CommentDto;
import com.example.springwebboard.entity.Article;
import com.example.springwebboard.service.ArticleService;
import com.example.springwebboard.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
public class ArticleController {

    @Autowired // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결!
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm dto) {
        log.info(dto.toString());
        Article created = articleService.create(dto);

        return "redirect:/articles/" + created.getId();
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {

        // 1. id로 데이터를 가져온다.
        Article article = articleService.detail(id);
        List<CommentDto> commentDtos = commentService.index(id);

        // 2. 데이터를 모델에 등록
        model.addAttribute("article", article);
        model.addAttribute("commentDtos", commentDtos);

        // 3. 보여줄 페이지 리턴
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {

        // 1. 모든 아티클을 가져온다.
        List<Article> articleList = articleService.index();

        // 2. 가져온 아티클을 모델에 등록
        model.addAttribute("articleList", articleList);

        // 3. 뷰 페이지 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){

        // 1. 수정할 데이터를 가져온다.
        Article article= articleService.detail(id);

        // 2. 모델에 데이터 등록.
        model.addAttribute("article",article);

        // 3. 뷰 페이지 설정
        return "articles/edit";

    }

    @PostMapping("/articles/update")
    public String update(ArticleForm dto){
        log.info(dto.toString());

        // 1. DTO -> Entity
        Article updated = articleService.update(dto, dto.getId());

        // 3 . 상세 페이지로 Redirect
        return "redirect:/articles/" + updated.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제요청 id:", id);

        // 1. 삭제
        if (articleService.delete(id)){
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        }

        // 2. index 페이지로 Redirect
        return "redirect:/articles";
    }
}
