package com.example.springwebboard.repository;

import com.example.springwebboard.entity.Article;
import com.example.springwebboard.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        /* Case 1: 1번 게시글의 모든 댓글 조회 */
        {
            // 데이터
            Long articleId = 1L;
            Article article = new Article(articleId, "aaaaa","1111");
            Comment a = new Comment(1L, article, "Seo", "재밌어요~");
            Comment b = new Comment(2L, article, "Kim", "흠...");
            Comment c = new Comment(3L, article, "Lee", "?!?");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 게시글의 모든 댓글을 출력!");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* Case 1: Seo 닉네임의 모든 댓글 조회 */
        {
            // 데이터
            String nickname = "Seo";
            Comment a = new Comment(1L, new Article(1L, "aaaaa","1111"), "Seo", "재밌어요~");
            Comment b = new Comment(4L, new Article(2L, "bbbbb","2222"), "Seo", "과자~");
            Comment c = new Comment(7L, new Article(3L, "ccccc","3333"), "Seo", "공부");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 검증
            assertEquals(expected.toString(), comments.toString());
        }
    }
}