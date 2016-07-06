package service.impl;

import dao.UserMapper;
import dao.UserRoleMapper;
import dao.condition.UserCondition;
import model.Resource;
import model.Role;
import model.User;
import model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.UserService;
import shiro.util.PasswordHelper;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
        //加密密码
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
    public void correlationRoles(Long userId, List<Long> roleIds) {
        if(userId == null || roleIds == null || roleIds.size() == 0)
            return;
        userRoleDao.insertMany(userId,roleIds);
    }

    @Override
    public void uncorrelationRoles(Long userId, List<Long> roleIds) {
        if(userId == null || roleIds == null || roleIds.size() == 0)
            return;
        userRoleDao.deleteMany(userId, roleIds);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.selectUserByName(username);
    }

    @Override
    public Set<Role> findRoles(String username) {
        return userDao.selectRolesByUsername(username);
    }

    @Override
    public Set<Resource> findResources(String username) {
        return userDao.selectResourcesByUsername(username);
    }

    @Override
    public Set<String> findRolesNames(String username) {
        Set<String > rs = userDao.selectRoleNamesByUsername(username);
        return rs;
    }

    @Override
    public Set<String> findResourcePermissions(String username) {
        Set<String> permissions = userDao.selectResourcePermissionsByUsername(username);
        return permissions;
    }

    @Override
    public boolean deleteUser(List<Long> userIds) {
        if(userIds == null || userIds.size() == 0)
            return true;
        for(Long userId : userIds) {
            userDao.deleteByPrimaryKey(userId);
            userRoleDao.deleteManyByUserId(userId);
        }
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        if(user == null)
            return true;
        User oldUser = userDao.selectByPrimaryKey(user.getId());
        if(oldUser == null)
            return false;
        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhone(user.getPhone());
        oldUser.setPassword(user.getPassword());
        oldUser.setDeleted(user.getDeleted());
        oldUser.setLocked(user.getLocked());
        if(user.getPassword() != null)
            passwordHelper.equals(oldUser);
        userDao.updateByPrimaryKeySelective(oldUser);
        return true;
    }

    @Override
    public boolean updateUser(User user, List<Long> roleIds) {
        if(user == null)
            return true;
        User oldUser = userDao.selectByPrimaryKey(user.getId());
        if(oldUser == null)
            return false;
        oldUser.setUsername(user.getUsername());
        oldUser.setEmail(user.getEmail());
        oldUser.setPhone(user.getPhone());
        oldUser.setPassword(user.getPassword());
        oldUser.setDeleted(user.getDeleted());
        oldUser.setLocked(user.getLocked());
        if(user.getPassword() != null)
            passwordHelper.equals(oldUser);
        userDao.updateByPrimaryKeySelective(oldUser);

        List<Long> delete_roles = userDao.selectRoleIdsByUsername(oldUser.getUsername());
        List<Long> add_roles = new LinkedList<Long>();

        if(delete_roles != null && delete_roles.size() > 0 && roleIds != null && roleIds.size() > 0) {
            for(Long roleId : roleIds) {
                if(delete_roles.contains(roleId)) {
                    delete_roles.remove(roleId);
                }
                else {
                    add_roles.add(roleId);
                }
            }
        }
        correlationRoles(oldUser.getId(), add_roles);
        uncorrelationRoles(oldUser.getId(), delete_roles);
        return true;
    }

    @Override
    public List<User> query(UserCondition userCondition) {
        return userDao.selectByCondition(userCondition);
    }
}
