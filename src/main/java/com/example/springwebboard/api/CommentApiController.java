package com.example.springwebboard.api;

import com.example.springwebboard.dto.CommentDto;
import com.example.springwebboard.entity.Comment;
import com.example.springwebboard.service.CommentService;
import org.apache.coyote.Response;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {

    @Autowired
    CommentService commentService;

    // 특정 아티클의 댓글 리스트
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> index(@PathVariable Long articleId){
        List<CommentDto> dtos = commentService.index(articleId);
        return (dtos != null) ?
                ResponseEntity.status(HttpStatus.OK).body(dtos) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
                          @RequestBody CommentDto dto) {
        CommentDto created = commentService.create(articleId, dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
