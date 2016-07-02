package service.impl;

import dao.FrockMapper;
import dao.condition.FrockCondition;
import model.Frock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.FrockService;

import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
@Service
public class FrockServiceImpl implements FrockService {

    @Autowired
    private FrockMapper frockDao;

    @Override
    public boolean add(Frock frock) {
        if(frockDao.insertSelective(frock) == 1)
            return true;
        return false;
    }

    @Override
    public boolean delete(List<Integer> ids) {
        if(frockDao.deleteMany(ids) >= 1)
            return true;
        return false;
    }

    @Override
    public boolean edit(Frock newFrock) {
        Frock oldFrock = frockDao.selectByPrimaryKey(newFrock.getId());
        if(oldFrock == null)
            return false;
        if(frockDao.updateByPrimaryKeySelective(newFrock) == 1)
            return true;
        return false;
    }

    @Override
    public List<Frock> query(FrockCondition condition) {
        if(condition.getSortby() == null || "".equals(condition.getSortby()))
            condition.setSortby("publish_date");
        return frockDao.selectByCondition(condition);
    }

    @Override
    public Frock queryDetail(Integer id) {
        return frockDao.selectByPrimaryKey(id);
    }
}
