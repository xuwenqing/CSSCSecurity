package service;

import dao.condition.NewsCondition;
import model.News;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by wenqing on 2016/6/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class NewsServiceTest {
    @Autowired
    private NewsService newsService;

    @Test
    public void add() {
        News news = new News();
        news.setAuthor("徐文清");
        news.setTitle("新闻标题");
        news.setContent("巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴");
        news.setCreatetime(new Date());
        news.setStatus(0);
        news.setNewsclassid(55);
        Assert.assertTrue(newsService.add(news));
    }

    @Test
    public void edit() {
        News news = new News();
        news.setId(67);
        news.setTitle("新的新闻标题");
        newsService.edit(news);
    }

    @Test
    public void queryByCondition() {
        int start = 0;
        int limit = 5;
        String sortby = "createTime";
        String order = "desc";
        String title = null;
        String orderName = "测试栏目";
        NewsCondition condition = new NewsCondition();
        condition.setStart(start);
        condition.setLimit(limit);
        condition.setSortby(sortby);
        condition.setOrder(order);
        condition.setTitle(title);
//        condition.setOrderName(orderName);
        System.out.println(newsService.query(condition));

    }

    @Test
    public  void queryDetail() {
        System.out.println(newsService.queryDetail(67));
    }

}
