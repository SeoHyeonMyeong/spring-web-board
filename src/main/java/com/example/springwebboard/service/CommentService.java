package com.example.springwebboard.service;

import com.example.springwebboard.dto.CommentDto;
import com.example.springwebboard.entity.Article;
import com.example.springwebboard.entity.Comment;
import com.example.springwebboard.repository.ArticleRepository;
import com.example.springwebboard.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ArticleRepository articleRepository;

    public List<CommentDto> index(Long articleId){
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.create(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto){
        // 게시글 조회
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() ->new IllegalArgumentException("댓글 생성 실패: 대상 게시물이 없습니다."));

        // 댓글 객체 생성
        Comment comment = Comment.create(dto, article);

        // DB에 저장
        Comment created = commentRepository.save(comment);

        // DTO로 반환
        return CommentDto.create(created);
    }

    @Transactional
    public CommentDto update(CommentDto dto, Long id){
        // 게시글 조회
        Article article = articleRepository.findById(dto.getArticleId())
                .orElseThrow(() ->new IllegalArgumentException("댓글 수정 실패: 대상 게시물이 없습니다."));

        // 댓글 객체 생성
        Comment comment = Comment.create(dto, article);
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패: 대상 댓글이 없습니다"));

        // 댓글 업데이트
        target.patch(comment);

        // DB에 저장
        Comment updated = commentRepository.save(target);

        // DTO로 반환
        return CommentDto.create(updated);
    }

    @Transactional
    public void delete(Long articleId, Long id){
        // 게시글 조회
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() ->new IllegalArgumentException("댓글 삭제 실패: 대상 게시물이 없습니다."));

        // 댓글 조회
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패: 대상 댓글이 없습니다."));

        // DB에서 댓글 삭제
        commentRepository.delete(comment);
    }
}
