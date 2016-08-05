package dao;

import dao.condition.RoleCondition;
import model.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectAll();

    List<Long> selectAllIds();

    List<Role> selectByCondition(RoleCondition condition);

    int selectCountByCondition(RoleCondition condition);

}