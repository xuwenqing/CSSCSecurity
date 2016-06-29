package service;

import dao.condition.HealthCondition;
import model.Health;

import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
public interface HealthService {
    boolean add(Health health);
    boolean delete(List<Integer> ids);
    boolean edit(Health newHealth);
    List<Health> query(HealthCondition condition);
}
