package dao;

import model.Technique;

public interface TechniqueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Technique record);

    int insertSelective(Technique record);

    Technique selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Technique record);

    int updateByPrimaryKeyWithBLOBs(Technique record);

    int updateByPrimaryKey(Technique record);
}