package com.example.springwebboard.entity;

import lombok.*;

import javax.persistence.*;

@Entity // DB가 해당 객체를 인식 가능!
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // 1,2,3 ... 자동 생성 어노테이션
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.content != null)
            this.content = article.content;
    }
}
