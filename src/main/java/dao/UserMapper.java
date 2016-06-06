package dao;

import model.Resource;
import model.Role;
import model.User;

import java.util.List;
import java.util.Set;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByName(String name);

    Set<Role> selectRolesByUsername(String name);

    List<Long> selectRoleIdsByUsername(String name);

    Set<String> selectRoleNamesByUsername(String name);

    Set<Resource> selectResourcesByUsername(String name);

    Set<String> selectResourcePermissionsByUsername(String name);

    List<User> selectAll();
}