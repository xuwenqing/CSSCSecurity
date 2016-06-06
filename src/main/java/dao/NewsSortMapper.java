package dao;

import model.NewsSort;

public interface NewsSortMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NewsSort record);

    int insertSelective(NewsSort record);

    NewsSort selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsSort record);

    int updateByPrimaryKey(NewsSort record);

    NewsSort selectByName(String name);
}