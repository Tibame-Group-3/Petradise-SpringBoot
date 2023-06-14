package tw.idv.morgan.news.model.service;

import tw.idv.morgan.news.model.vo.News;

import java.util.List;

public interface NewsService {
    News insert(News newNews);
    News update(News news);
    News getOneById(Integer newsId);
    List<News> getAll();

}
