package dao;

import model.Resource;
import model.Role;
import model.User;
import model.UserRole;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.UserService;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by wenqing on 2016/6/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-config.xml"})
public class UserDaoTest {

    @Autowired
    private UserMapper userDao;
    @Autowired
    private UserRoleMapper userRoleDao;
    @Autowired
    private RoleMapper roleDao;

    @Autowired UserService userService;



    @Test
    public void selectRolesByName() {
        Set<Role> roles = userDao.selectRolesByUsername("test1");
        System.out.println(roles);
    }

    @Test
    public void selectResourcesByName() {
        Set<Resource> resources = userDao.selectResourcesByUsername("admin");
        System.out.println(resources);
    }

    @Test
    public void insertManyUserRole() {
        User user = userDao.selectUserByName("test1");
        Long user_id = user.getId();
        List<Long> role_ids = roleDao.selectAllIds();

        System.out.println(userRoleDao.insertMany(user_id, role_ids));

    }

    @Test
    public void deleteManyUserRole() {
        User user = userDao.selectUserByName("test1");
        Long user_id = user.getId();
        Set<Role> roles = userService.findRoles("test1");

        List<Long> role_ids = new LinkedList<Long>();
        for(Role r : roles) {
            role_ids.add(r.getId());
        }
        userService.uncorrelationRoles(user_id, role_ids);

    }
}
