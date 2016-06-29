package dao;

import model.Health;

public interface HealthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Health record);

    int insertSelective(Health record);

    Health selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Health record);

    int updateByPrimaryKeyWithBLOBs(Health record);

    int updateByPrimaryKey(Health record);
}