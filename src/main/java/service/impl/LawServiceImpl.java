package service.impl;

import dao.LawMapper;
import dao.condition.LawCondition;
import model.Law;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.LawService;

import java.util.List;

/**
 * Created by wenqing on 2016/5/29.
 */
@Service
public class LawServiceImpl implements LawService {
    @Autowired
    private LawMapper lawDao;

    @Override
    public boolean add(Law law) {
        if(lawDao.insertSelective(law) == 1)
            return true;
        return false;
    }

    @Override
    public boolean delete(List<Integer> ids) {
        if(lawDao.deleteMany(ids) >= 1)
            return true;
        return false;
    }

    @Override
    public boolean edit(Law newLaw) {
        Law oldLaw = lawDao.selectByPrimaryKey(newLaw.getId());
        if(oldLaw == null)
            return false;
        if(lawDao.updateByPrimaryKeySelective(newLaw) == 1)
            return true;
        return false;
    }

    @Override
    public List<Law> query(LawCondition condition) {
        if(condition.getSortby() == null || "".equals(condition.getSortby()))
            condition.setSortby("publish_date");
        return lawDao.selectByCondition(condition);
    }

    @Override
    public int queryCount(LawCondition condition) {
        return lawDao.selectCountByCondition(condition);
    }

    @Override
    public Law queryDetail(Integer id) {
        return lawDao.selectByPrimaryKey(id);
    }
}
