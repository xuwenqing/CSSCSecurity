package dao;

import dao.condition.RegisterCondition;
import model.Register;

import java.util.List;

public interface RegisterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Register record);

    int insertSelective(Register record);

    Register selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Register record);

    int updateByPrimaryKey(Register record);

    Register selectByName(String name);
    List<Register> selectAll();

    List<Register> selectByRegisterCondition(RegisterCondition condition);
    long selectConditionCount(RegisterCondition condition);
}