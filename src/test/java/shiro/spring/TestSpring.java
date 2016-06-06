package shiro.spring;

import dao.ResourceMapper;
import model.Resource;
import model.Role;
import model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.ResourceService;
import service.RoleService;
import service.UserService;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wenqing on 2016/6/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class TestSpring {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private ResourceMapper resourceMapper;

    @Test
    public void initUser() {

      //  userDao.selectResourcesByName();
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setSalt("63274632");
        user.setLocked(false);
        user.setDeleted(false);
        user.setCreateDate(new Date());
        User newuser = userService.createUser(user);

    }

    @Test
    public void initRole() {
        Role r1 = new Role();
        r1.setRole("admin");
        r1.setDescription("管理员");
        r1.setAvailable(Boolean.TRUE);
        Role r2 = new Role();
        r2.setRole("user");
        r2.setDescription("用户管理员");
        r2.setAvailable(Boolean.TRUE);
        roleService.createRole(r1);
        roleService.createRole(r2);
    }

    @Test
    public void initRoleResource() {
        List<Resource> resources =  resourceMapper.selectAll();
        List<Long> r_ids = new LinkedList<Long>();
        for(Resource r : resources)
            r_ids.add(r.getId());

        roleService.correlationResources(1L,r_ids);
        //System.out.println(resources);
    }


}
