package com.example.springwebboard.repository;

import com.example.springwebboard.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

}
