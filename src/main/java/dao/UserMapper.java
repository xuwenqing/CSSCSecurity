package dao;

import model.Resource;
import model.Role;
import model.User;

import java.util.Set;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByName(String name);
    Set<Role> selectRolesByName(String name);
    Set<Resource> selectResourcesByName(String name);
}