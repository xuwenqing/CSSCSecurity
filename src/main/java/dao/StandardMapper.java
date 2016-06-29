package dao;

import dao.condition.StandardCondition;
import model.Standard;

import java.util.List;

public interface StandardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Standard record);

    int insertSelective(Standard record);

    Standard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Standard record);

    int updateByPrimaryKeyWithBLOBs(Standard record);

    int updateByPrimaryKey(Standard record);

    int deleteMany(List<Integer> ids);

    List<Standard> selectByCondition(StandardCondition condition);
}