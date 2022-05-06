package com.example.springwebboard.dto;

import com.example.springwebboard.entity.Article;
import com.example.springwebboard.entity.Comment;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class CommentDto {

    private Long id;

    @JsonProperty("article_id")
    private Long articleId;
    private String nickname;
    private String body;

    public static CommentDto create(Comment entity) {
        return new CommentDto(
                entity.getId(),
                entity.getArticle().getId(),
                entity.getNickname(),
                entity.getBody()
        );
    }
}
