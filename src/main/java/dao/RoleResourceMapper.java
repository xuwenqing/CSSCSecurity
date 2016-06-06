package dao;

import model.RoleResource;

import java.util.List;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(RoleResource key);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    List<Long> selectResourceIdsByRoleId(Long roleId);

    int deleteManyByRoleId(Long roleId);
    int deleteManyByResourceId(Long resourceId);
}