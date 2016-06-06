package service;

import model.News;
import model.NewsSort;
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
public class NewsSortTest {
    @Autowired
    private NewsSortService newsSortService;

    @Autowired
    private NewsService newsService;

    @Test
    public void add() {
        NewsSort newsSort = new NewsSort();
        newsSort.setSortname("栏目1");
        newsSort.setSortlevel(0);
        newsSortService.add(newsSort);

    }

    @Test
    public void addNews() {
        News news = new News();
        news.setAuthor("徐文清");
        news.setTitle("新闻标题");
        news.setContent("巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴");
        news.setCreatetime(new Date());
        news.setStatus(0);
        news.setNewsclassid(59);
        Assert.assertTrue(newsService.add(news));
        news = new News();
        news.setAuthor("徐文清1");
        news.setTitle("新闻标题1");
        news.setContent("巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴巴拉巴拉巴");
        news.setCreatetime(new Date());
        news.setStatus(0);
        news.setNewsclassid(59);
        Assert.assertTrue(newsService.add(news));
    }

    @Test
    public void delete() {
        Assert.assertTrue(newsSortService.delete(59));
    }

    @Test
    public void update() {
        NewsSort newsSort = new NewsSort();
        newsSort.setId(59);
        newsSort.setSortname("修改栏目1");
        newsSortService.update(newsSort);

    }

    @Test
    public void queryById() {
        System.out.println(newsSortService.query(59));
    }
    @Test
    public void queryByName() {
        System.out.println(newsSortService.query("修改栏目1"));
    }

}
