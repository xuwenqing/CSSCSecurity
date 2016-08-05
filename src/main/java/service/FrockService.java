package service;

import dao.condition.FrockCondition;
import model.Frock;

import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
public interface FrockService {
    boolean add(Frock frock);
    boolean delete(List<Integer> ids);
    boolean edit(Frock newFrock);
    List<Frock> query(FrockCondition condition);
    int queryCount(FrockCondition condition);
    Frock queryDetail(Integer id);
}
