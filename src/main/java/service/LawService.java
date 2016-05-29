package service;


import model.Law;

/**
 * Created by wenqing on 2016/5/29.
 */
public interface LawService {
    boolean add(Law law);
    boolean delete(int id);
    Law getById(int id);
}
