package service;

import dao.condition.AccidentCondition;
import dao.condition.StandardCondition;
import model.Accident;
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
public class AccidentTest {
    @Autowired
    private AccidentService accidentService;

    @Test
    public void add() {
        Accident s1 = new Accident();
        s1.setName("accident1");
        s1.setContent("content1");
        s1.setPublishDate(new Date());

        Accident s2 = new Accident();
        s2.setName("accident2");
        s2.setContent("content2");
        s2.setKeywords("keywords2");
        s2.setPublishDate(new Date());
        System.out.println(accidentService.add(s1));

        //System.out.println(accidentService.add(s2));
    }

    @Test
    public void delete() {
        List<Integer> ids = new LinkedList<Integer>();
        ids.add(2);
        System.out.println(accidentService.delete(ids));
    }

    @Test
    public void update() {
        Accident s = new Accident();
        s.setId(1);
        s.setName("stand1-1");
        s.setKeywords("key");
        System.out.println(accidentService.edit(s));
    }

    @Test
    public void select() {
        AccidentCondition condition = new AccidentCondition();
        List<Accident> standards = accidentService.query(condition);
        System.out.println(standards.size());
    }
}
