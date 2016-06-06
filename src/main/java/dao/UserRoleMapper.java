package dao;

import model.User;
import model.UserRole;

import java.util.Set;

public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRole key);

    int insert(UserRole record);

    int insertSelective(UserRole record);
}