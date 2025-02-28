package com.my.articles.controller;

import com.my.articles.dto.ArticleDTO;
import com.my.articles.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("articles")
@Slf4j
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping({"", "/"})
    public String showAllArticles(Model model) {
        List<ArticleDTO> articles = articleService.getAllArticle();
        model.addAttribute("articleList",articles);
        return "/articles/show_all";
    }

    @GetMapping("one")
    public String one() {
        return "/articles/show";
    }

    @GetMapping("{id}")
    public String showOneArticles(@PathVariable("id")Long id, Model model) {
        ArticleDTO dto = articleService.getOneArticle(id);
        model.addAttribute("dto",dto);
        return "/articles/show";
    }

    @GetMapping("new")
    public String newAllArticles() {
        return "/articles/new";
    }

    @PostMapping("create")
    public ModelAndView createArticle(ArticleDTO articleDTO) {
        String url = "redirect:/articles";

        articleService.insertArticle(articleDTO);

        return new ModelAndView(url);
    }

    @GetMapping("update")
    public String updateAllArticles() {
        return "/articles/update";
    }

    @GetMapping("{id}/delete")
    public String deleteArticle(@PathVariable("id")Long id, RedirectAttributes redirectAttributes) {
        articleService.deleteArticle(id);
        String msg = "정상적으로 삭제되었습니다.";
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/articles";
    }

    @GetMapping("{id}/update")
    public String updateArticle(@PathVariable("id")Long id,Model model) {
        model.addAttribute("dto", articleService.getOneArticle(id));
        return "/articles/update";
    }

    @PostMapping("update")
    public String updateArticle(ArticleDTO dto) {
        String url = "redirect:/articles/" + dto.getId();
        log.info("--------------" + dto);
        articleService.updateArticle(dto);
        return url;
    }
}
