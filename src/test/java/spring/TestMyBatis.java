package spring;

import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.UserService;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})

public class TestMyBatis {
    private static Logger logger = Logger.getLogger(TestMyBatis.class);
    //  private ApplicationContext ac = null;
    @Resource
    private UserService userService = null;

//  @Before
//  public void before() {
//      ac = new ClassPathXmlApplicationContext("spring-mybatis.xml");
//      userService = (IUserService) ac.getBean("userService");
//  }

    @Test
    public void test1() {
        User user = new User();
        user.setUsername("11211");
        user.setPhone("222");
        user.setPassword("333");
        user.setRole("444");

        int result = userService.insert(user);
        logger.info("user" + result);
//        User user = userService.getUserById(1);
//        // System.out.println(user.getUserName());
//        // logger.info("值："+user.getUserName());
//        logger.info(JSON.toJSONString(user));
    }
}