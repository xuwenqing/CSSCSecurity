package service;

import dao.condition.LawCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wenqing on 2016/6/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class LawTest {
    @Autowired
    private LawService lawService;
    @Test
    public void select() {
        LawCondition condition = new LawCondition();
        lawService.query(condition);
    }
}
