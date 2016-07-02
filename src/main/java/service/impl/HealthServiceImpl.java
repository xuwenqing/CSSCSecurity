package service.impl;

import dao.HealthMapper;
import dao.condition.HealthCondition;
import model.Health;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.HealthService;

import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
@Service
public class HealthServiceImpl implements HealthService{

    @Autowired
    private HealthMapper healthDao;

    @Override
    public boolean add(Health health) {
        if(healthDao.insertSelective(health) == 1)
            return true;
        return false;
    }

    @Override
    public boolean delete(List<Integer> ids) {
        if(healthDao.deleteMany(ids) >= 1)
            return true;
        return false;
    }

    @Override
    public boolean edit(Health newHealth) {
        Health oldHealth = healthDao.selectByPrimaryKey(newHealth.getId());
        if(oldHealth == null)
            return false;
        if(healthDao.updateByPrimaryKeySelective(newHealth) == 1)
            return true;
        return false;
    }

    @Override
    public List<Health> query(HealthCondition condition) {
        if(condition.getSortby() == null || "".equals(condition.getSortby()))
            condition.setSortby("publish_date");
        return healthDao.selectByCondition(condition);
    }

    @Override
    public Health queryDetail(Integer id) {
        return healthDao.selectByPrimaryKey(id);
    }
}
