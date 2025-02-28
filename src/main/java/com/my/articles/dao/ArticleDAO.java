package com.my.articles.dao;

import com.my.articles.entity.Article;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDAO {
    @Autowired
    EntityManager em;

    public List<Article> getAllArticles() {
        String sql = "SELECT a FROM Article AS a ORDER BY a.id DESC";
        List<Article> articles = em.createQuery(sql).getResultList();
        return articles;
    }

    public void insertArticle(Article article) {
        em.persist(article);
    }

    public Article getOneArticle(Long id) {
        return em.find(Article.class,id);
    }

    public void deleteArticle(Long id) {
        Article article = em.find(Article.class,id);

        em.remove(article);
    }

    public void updateArticle(Article article) {
        Article original = em.find(Article.class,article.getId());

        original.setTitle(article.getTitle());
        original.setContent(article.getContent());
    }
}
