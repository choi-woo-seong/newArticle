package com.my.articles.dto;

import com.my.articles.entity.Article;
import com.my.articles.entity.Comment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {
    private Long id;
    private String nickname;
    private String body;

    public static CommentDTO fromEntity(Comment comment) {
        return new CommentDTO(comment.getId(), comment.getNickname(), comment.getBody());
    }

    public static Comment fromDto(CommentDTO commentDTO) {
        Comment comment = new Comment();

        comment.setId(commentDTO.getId());
        comment.setNickname(commentDTO.getNickname());
        comment.setBody(commentDTO.getBody());

        return comment;
    }
}
