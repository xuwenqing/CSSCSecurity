package dao;

import dao.condition.AccidentCondition;
import model.Accident;

import java.util.List;

public interface AccidentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Accident record);

    int insertSelective(Accident record);

    Accident selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Accident record);

    int updateByPrimaryKeyWithBLOBs(Accident record);

    int updateByPrimaryKey(Accident record);

    int deleteMany(List<Integer> ids);

    List<Accident> selectByCondition(AccidentCondition condition);

    int selectCountByCondition(AccidentCondition condition);
}