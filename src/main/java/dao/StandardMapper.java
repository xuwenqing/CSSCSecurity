package dao;

import model.Standard;

public interface StandardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Standard record);

    int insertSelective(Standard record);

    Standard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Standard record);

    int updateByPrimaryKeyWithBLOBs(Standard record);

    int updateByPrimaryKey(Standard record);
}