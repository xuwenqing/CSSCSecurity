package dao;

import dao.condition.NewsCondition;
import model.News;

import java.util.List;

public interface NewsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);

    List<News> selectByCondition(NewsCondition condition);
    List<Integer> selectByClassId(int id);
    int deleteMany(List<Integer> ids);


}