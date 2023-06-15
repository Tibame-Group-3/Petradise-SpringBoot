package tw.idv.petradisespringboot.news.controller;

import tw.idv.petradisespringboot.news.model.service.NewsServiceImpl;
import tw.idv.petradisespringboot.news.model.vo.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsListController {

    private final NewsServiceImpl newsListService;

    @Autowired
    public NewsListController(NewsServiceImpl newsListService) {
        this.newsListService = newsListService;
    }

    @PostMapping ("/news/add")
    public News insert(@RequestBody News news) {
        return newsListService.insert(news);
    }

    @PutMapping ("/news/update/{newsId}")
    public News update(@PathVariable("newsId") Integer newsId, @RequestBody News news) {
        News existingNews = newsListService.getOneById(newsId);
        if (existingNews != null) {
            news.setNewsId(newsId);
            newsListService.update(news);
        }
        return news;
    }

    @GetMapping("/news/get/{newsId}")
    public News getNewsById(@PathVariable("newsId") Integer newsId) {
        return newsListService.getOneById(newsId);
    }

    @GetMapping("/news/get/all")
    public List<News> getAll() {
        return newsListService.getAll();
    }
}
