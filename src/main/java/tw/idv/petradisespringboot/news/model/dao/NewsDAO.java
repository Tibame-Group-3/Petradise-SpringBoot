package tw.idv.morgan.news.model.dao;

import tw.idv.morgan.news.model.vo.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsDAO extends JpaRepository<News, Integer> {
}
