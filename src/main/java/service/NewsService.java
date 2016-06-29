package service;

import dao.condition.NewsCondition;
import model.News;

import java.util.List;

/**
 * Created by wenqing on 2016/6/6.
 */
public interface NewsService {
    boolean add(News news);
    boolean delete(List<Integer> ids);
    boolean edit(News oldNews);
    List<News> query(NewsCondition condition);
    News queryDetail(int id);
}
