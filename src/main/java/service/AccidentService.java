package service;

import dao.condition.AccidentCondition;
import model.Accident;

import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
public interface AccidentService {
    boolean add(Accident accident);
    boolean delete(List<Integer> ids);
    boolean edit(Accident newAccident);
    List<Accident> query(AccidentCondition condition);
    Accident queryDetail(Integer id);
}
