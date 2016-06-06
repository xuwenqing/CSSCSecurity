package service;

import model.NewsSort;

/**
 * Created by wenqing on 2016/6/6.
 */
public interface NewsSortService {
    boolean add(NewsSort newsSort);
    boolean delete(int id);
    boolean update(NewsSort newsSort);
    NewsSort query(String name);
    NewsSort query(int id);
}
