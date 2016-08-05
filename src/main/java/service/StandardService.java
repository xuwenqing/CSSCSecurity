package service;

import dao.condition.StandardCondition;
import model.Standard;

import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
public interface StandardService {

    boolean add(Standard standard);
    boolean delete(List<Integer> ids);
    boolean edit(Standard newStandard);
    List<Standard> query(StandardCondition condition);
    int queryCount(StandardCondition condition);
    Standard queryDetail(Integer id);

}
