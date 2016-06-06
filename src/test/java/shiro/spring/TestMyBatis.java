package shiro.spring;

import dao.LawMapper;
import dao.RegisterMapper;
import model.Law;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestMyBatis {

    @Autowired
    private RegisterMapper registerDao;
    @Autowired
    private LawMapper lawDao;

    private static Logger logger = Logger.getLogger(TestMyBatis.class);

    @Test
    public void registerConditionTest() {
//        RegisterCondition condition = new RegisterCondition();
//        condition.setSortby("register_time");
//        condition.setOrder("desc");
//        condition.setLimit(2);
//        condition.setStart(0);
//        DateConverter converter = new DateConverter();
//        String startTime = "2016-5-1";
//        String endTime = "2016-5-2";
//        if(startTime != null) {
//            condition.setStartTime(converter.convert(startTime));
//        }
//        if(startTime != null) {
//            condition.setEndTime(converter.convert(endTime));
//        }
//        List<Register> registers = registerDao.selectAll();
//        System.out.println(registers);
//        registers = registerDao.selectByRegisterCondition(condition);
//        System.out.println(registers);
    }

    @Test
    public void lawTest() {
        Law law = lawDao.selectByPrimaryKey(1);
        System.out.println(law);
    }
}