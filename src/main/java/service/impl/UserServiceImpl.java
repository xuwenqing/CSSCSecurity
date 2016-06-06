package service.impl;

import dao.UserMapper;
import dao.UserRoleMapper;
import model.Resource;
import model.Role;
import model.User;
import model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;
import shiro.util.PasswordHelper;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by wenqing on 2016/6/5.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userDao;
    @Autowired
    private UserRoleMapper userRoleDao;
    private PasswordHelper passwordHelper = new PasswordHelper();

    @Override
    public User createUser(User user) {
        //º”√‹√‹¬Î
        passwordHelper.encryptPassword(user);
        if(userDao.insertSelective(user) == 1)
            return user;
        return null;
    }

    @Override
    public void changePassword(Long userId, String newPassword) {
        User user = userDao.selectByPrimaryKey(userId);
        if(user != null) {
            user.setPassword(newPassword);
            passwordHelper.encryptPassword(user);
            userDao.updateByPrimaryKeySelective(user);
        }
    }

    @Override
    public void correlationRoles(Long userId, Long... roleIds) {
        if(userId == null || roleIds.length == 0)
            return;
        for(Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleDao.insertSelective(userRole);
        }
    }

    @Override
    public void uncorrelationRoles(Long userId, Long... roleIds) {
        if(userId == null || roleIds.length == 0)
            return;
        for(Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleDao.deleteByPrimaryKey(userRole);
        }
    }

    @Override
    public User findByUsername(String username) {
        return userDao.selectUserByName(username);
    }

    @Override
    public Set<Role> findRoles(String username) {
        return userDao.selectRolesByName(username);
    }

    @Override
    public Set<Resource> findResources(String username) {
        return userDao.selectResourcesByName(username);
    }

    @Override
    public Set<String> findRolesNames(String username) {
        Set<Role> roles = findRoles(username);
        Set<String > rs = new HashSet<String>();
        for(Role role : roles) {
            rs.add(role.getRole());
        }
        return rs;
    }

    @Override
    public Set<String> findResourcePermissions(String username) {
        Set<Resource> resources = findResources(username);
        Set<String> permissions = new HashSet<String>();
        for (Resource resource : resources) {
            permissions.add(resource.getPermission());
        }
        return permissions;
    }
}
