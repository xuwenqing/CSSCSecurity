package dao;

import dao.condition.LawCondition;
import model.Law;

import java.util.List;

public interface LawMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Law record);

    int insertSelective(Law record);

    Law selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Law record);

    int updateByPrimaryKeyWithBLOBs(Law record);

    int updateByPrimaryKey(Law record);

    int deleteMany(List<Integer> ids);

    List<Law> selectByCondition(LawCondition condition);

    int selectCountByCondition(LawCondition condition);
}