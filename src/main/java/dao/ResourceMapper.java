package dao;

import com.sun.org.apache.bcel.internal.generic.LNEG;
import model.Resource;

import java.util.List;

public interface ResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Resource record);

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);

    List<Resource> selectAll();

    List<Resource> selectByRoleId(Long id);
}