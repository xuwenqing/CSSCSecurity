package dao;

import model.Law;

public interface LawMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Law record);

    int insertSelective(Law record);

    Law selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Law record);

    int updateByPrimaryKeyWithBLOBs(Law record);

    int updateByPrimaryKey(Law record);
}