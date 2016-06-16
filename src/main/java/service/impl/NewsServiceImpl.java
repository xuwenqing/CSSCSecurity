package service.impl;

import dao.NewsMapper;
import dao.NewsSortMapper;
import dao.condition.NewsCondition;
import model.News;
import model.NewsSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.NewsService;

import java.util.List;

/**
 * Created by wenqing on 2016/6/6.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsDao;
//    @Autowired
//    private NewsSortMapper newsSortDao;

    @Override
    public boolean add(News news) {
        if(newsDao.insertSelective(news) == 1)
            return true;
        return false;
    }

    @Override
    public boolean delete(List<Integer> ids) {
        if(ids == null || ids.isEmpty())
            return true;
        if(newsDao.deleteMany(ids) > 0)
            return true;
        return false;
    }

    @Override
    public boolean edit(News news) {
        News oldNews = newsDao.selectByPrimaryKey(news.getId());
        if(oldNews == null)
            return false;
        if(newsDao.updateByPrimaryKeySelective(news) == 1)
            return true;
        return false;
    }

    @Override
    public List<News> query(NewsCondition condition) {
        return newsDao.selectByCondition(condition);
    }

    @Override
    public News queryDetail(int id) {
        return newsDao.selectByPrimaryKey(id);
    }
}
