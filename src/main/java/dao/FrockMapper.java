package dao;

import dao.condition.FrockCondition;
import model.Frock;

import java.util.List;

public interface FrockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Frock record);

    int insertSelective(Frock record);

    Frock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Frock record);

    int updateByPrimaryKeyWithBLOBs(Frock record);

    int updateByPrimaryKey(Frock record);

    int deleteMany(List<Integer> ids);

    List<Frock> selectByCondition(FrockCondition condition);
}