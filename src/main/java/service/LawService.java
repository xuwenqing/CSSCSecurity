package service;


import dao.condition.LawCondition;
import model.Law;

import java.util.List;

/**
 * Created by wenqing on 2016/5/29.
 */
public interface LawService {
    boolean add(Law law);
    boolean delete(List<Integer> ids);
    boolean edit(Law newLaw);
    List<Law> query(LawCondition condition);
}
