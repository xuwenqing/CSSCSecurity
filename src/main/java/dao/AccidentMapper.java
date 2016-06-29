package dao;

import model.Accident;

public interface AccidentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Accident record);

    int insertSelective(Accident record);

    Accident selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Accident record);

    int updateByPrimaryKeyWithBLOBs(Accident record);

    int updateByPrimaryKey(Accident record);
}