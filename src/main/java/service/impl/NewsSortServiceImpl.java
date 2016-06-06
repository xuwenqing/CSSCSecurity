package service.impl;

import dao.NewsMapper;
import dao.NewsSortMapper;
import model.NewsSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.NewsSortService;

import java.util.List;

/**
 * Created by wenqing on 2016/6/6.
 */
@Service
public class NewsSortServiceImpl implements NewsSortService {
    @Autowired
    private NewsSortMapper newsSortDao;
    @Autowired
    private NewsMapper newsDao;

    @Override
    public boolean add(NewsSort newsSort) {
        if(newsSortDao.insertSelective(newsSort) == 1)
            return true;
        return false;
    }

    @Override
    public boolean delete(int id) {
        List<Integer> news_ids = newsDao.selectByClassId(id);
        if(news_ids != null && !news_ids.isEmpty()) {
            newsDao.deleteMany(news_ids);
        }
        if(newsSortDao.deleteByPrimaryKey(id) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(NewsSort newsSort) {
        if(newsSortDao.updateByPrimaryKeySelective(newsSort) == 1) {
            return true;
        }
        return false;
    }

    @Override
    public NewsSort query(String name) {
        return newsSortDao.selectByName(name);
    }

    @Override
    public NewsSort query(int id) {
        return newsSortDao.selectByPrimaryKey(id);
    }
}
