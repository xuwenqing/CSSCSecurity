package spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class TestMyBatis {
    private static Logger logger = Logger.getLogger(TestMyBatis.class);
    //  private ApplicationContext ac = null;
//    @Autowired Result Maps collection already contains value for dao.UserMapper.BaseResultMap
//    private UserService userService;

//  @Before
//  public void before() {
//      ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
//      userService = (IUserService) ac.getBean("userService");
//  }

    @Test
    public void test1() {
//        User user = new User();
//        user.setUsername("11211");
//        user.setPhone("222");
//        user.setPassword("333");
//        user.setRole("444");
//
//        int result = userService.insert(user);
//        logger.info("user" + result);
    }

    @Test
    public void test2() {
//        User user = userService.getUserById(1);
//        System.out.println(JSON.toJSON(user));
    }
}