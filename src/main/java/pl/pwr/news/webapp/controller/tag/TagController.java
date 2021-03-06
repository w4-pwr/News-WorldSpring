package pl.pwr.news.webapp.controller.tag;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.pwr.news.model.article.Article;
import pl.pwr.news.model.category.Category;
import pl.pwr.news.model.tag.Tag;
import pl.pwr.news.service.article.ArticleService;
import pl.pwr.news.service.category.CategoryService;
import pl.pwr.news.service.tag.TagService;

import java.util.List;

/**
 * Created by jf on 3/27/16.
 */
@Controller
@RequestMapping(value = "/admin/tag/")
@Log4j
public class TagController {

    @Autowired
    TagService tagService;

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/list")
    public String listTags(Model model) {
        List<Tag> tagList = tagService.findAll();
        model.addAttribute("tagList", tagList);

        return "tag/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createNewTag(Model model) {
        List<Category> categoryList = categoryService.findAll();
        List<Article> articleList = articleService.findAll();
        model.addAttribute("articleList", articleList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("newTag", new Tag());

        return "tag/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveNewTag(@ModelAttribute(value = "newTag") Tag tag) {
        tagService.createTag(tag);

        return "redirect:/admin/tag/add";
    }
}
