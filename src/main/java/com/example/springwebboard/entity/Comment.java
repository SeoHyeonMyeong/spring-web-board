package com.example.springwebboard.entity;

import com.example.springwebboard.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public void patch(CommentDto dto){
        if (dto.getId() != null && dto.getId() != this.id)
            throw new IllegalArgumentException("댓글 수정 실패 : 다른 id를 가진 수정 요청이 거부되었습니다.");
        if (dto.getArticleId() != null && dto.getArticleId() != this.article.getId())
            throw new IllegalArgumentException("댓글 수정 실패 : 다른 게시글 id를 가진 수정 요청이 거부되었습니다.");

        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if (dto.getBody() != null)
            this.body = dto.getBody();
    }

    public static Comment create(CommentDto dto, Article article){
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패 : 게시글의 id와 댓글의 article_id가 일치하지 않습니다.");

        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }
}
