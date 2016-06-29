package dao;

import dao.condition.TechniqueCondition;
import model.Technique;

import java.util.List;

public interface TechniqueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Technique record);

    int insertSelective(Technique record);

    Technique selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Technique record);

    int updateByPrimaryKeyWithBLOBs(Technique record);

    int updateByPrimaryKey(Technique record);

    int deleteMany(List<Integer> ids);

    List<Technique> selectByCondition(TechniqueCondition condition);
}