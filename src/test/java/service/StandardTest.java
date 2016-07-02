package service;

import dao.condition.StandardCondition;
import model.Standard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class StandardTest {
    @Autowired
    private StandardService standardService;

    @Test
    public void add() {
        Standard s1 = new Standard();
        s1.setName("stand1");
        s1.setContent("content1");
        s1.setPublishDate(new Date());

        Standard s2 = new Standard();
        s2.setName("独守空房123");
        s2.setContent("士大夫");
        s2.setKeywords("广泛大概");
        s2.setPublishDate(new Date());
        //System.out.println(standardService.add(s1));

        System.out.println(standardService.add(s2));
    }

    @Test
    public void delete() {
        List<Integer> ids = new LinkedList<Integer>();
        ids.add(2);
        System.out.println(standardService.delete(ids));
    }

    @Test
    public void update() {
        Standard s = new Standard();
        s.setId(3);
        s.setName("stand1-1");
        s.setKeywords("key");
        System.out.println(standardService.edit(s));
    }

    @Test
    public void select() {
        StandardCondition condition = new StandardCondition();
        condition.setKeywords("key");
        condition.setStart(1);
        condition.setLimit(3);
        List<Standard> standards = standardService.query(condition);
        System.out.println(standards.size());
    }
}
