package com.my.articles.dto;

import com.my.articles.entity.Article;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    private Long id;
    private String title;
    private String content;
    private List<CommentDTO> comments = new ArrayList<>();

    public static ArticleDTO fromEntity(Article article) {
        return new ArticleDTO(article.getId(), article.getTitle(), article.getContent(),article.getCommentList().stream().map(x -> CommentDTO.fromEntity(x)).toList());
    }

    public static Article fromDto(ArticleDTO articleDTO) {
        Article article = new Article();

        article.setId(articleDTO.getId());
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());

        return article;
    }
}
