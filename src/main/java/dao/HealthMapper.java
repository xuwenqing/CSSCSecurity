package dao;

import dao.condition.HealthCondition;
import model.Health;

import java.util.List;

public interface HealthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Health record);

    int insertSelective(Health record);

    Health selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Health record);

    int updateByPrimaryKeyWithBLOBs(Health record);

    int updateByPrimaryKey(Health record);

    int deleteMany(List<Integer> ids);

    List<Health> selectByCondition(HealthCondition condition);

    int selectCountByCondition(HealthCondition condition);
}