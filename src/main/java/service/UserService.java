package service;

import dao.condition.UserCondition;
import model.Resource;
import model.Role;
import model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    /**
     * 创建用户
     * @param user
     */
     User createUser(User user);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
     void changePassword(Long userId, String newPassword);

    /**
     * 添加用户-角色关系
     * @param userId
     * @param roleIds
     */
     void correlationRoles(Long userId, List<Long> roleIds);


    /**
     * 移除用户-角色关系
     * @param userId
     * @param roleIds
     */
     void uncorrelationRoles(Long userId, List<Long> roleIds);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
     User findByUsername(String username);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
     Set<Role> findRoles(String username);

     Set<Role> findRoles(Long id);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
     Set<Resource> findResources(String username);

     Set<String> findRolesNames(String username);

     Set<String> findResourcePermissions(String username);

     boolean deleteUser(List<Long> userId);

     boolean updateUser(User user);

     boolean updateUser(User user,List<Long> roleIds);

     List<User> query(UserCondition userCondition);

    int queryCount(UserCondition condition);
}
