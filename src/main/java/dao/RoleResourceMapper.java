package dao;

import model.RoleResource;

public interface RoleResourceMapper {
    int deleteByPrimaryKey(RoleResource key);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);
}