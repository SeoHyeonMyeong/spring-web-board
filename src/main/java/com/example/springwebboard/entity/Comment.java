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

    public void patch(Comment comment){
        if (comment.nickname != null)
            this.nickname = comment.nickname;
        if (comment.body != null)
            this.body = comment.body;
    }

    public static Comment create(CommentDto dto, Article article){
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );
    }
}
