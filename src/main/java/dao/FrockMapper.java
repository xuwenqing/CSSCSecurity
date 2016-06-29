package dao;

import model.Frock;

public interface FrockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Frock record);

    int insertSelective(Frock record);

    Frock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Frock record);

    int updateByPrimaryKeyWithBLOBs(Frock record);

    int updateByPrimaryKey(Frock record);
}