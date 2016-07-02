package service.impl;

import dao.AccidentMapper;
import dao.condition.AccidentCondition;
import model.Accident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.AccidentService;

import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
@Service
public class AccidentServiceImpl implements AccidentService {
    @Autowired
    private AccidentMapper accidentDao;

    @Override
    public boolean add(Accident accident) {
        if(accidentDao.insertSelective(accident) == 1)
            return true;
        return false;
    }

    @Override
    public boolean delete(List<Integer> ids) {
        if(accidentDao.deleteMany(ids) >= 1)
            return true;
        return false;
    }

    @Override
    public boolean edit(Accident newAccident) {
        Accident oldAccident = accidentDao.selectByPrimaryKey(newAccident.getId());
        if(oldAccident == null)
            return false;
        if(accidentDao.updateByPrimaryKeySelective(newAccident) == 1)
            return true;
        return false;
    }

    @Override
    public List<Accident> query(AccidentCondition condition) {
        if(condition.getSortby() == null || "".equals(condition.getSortby()))
            condition.setSortby("publish_date");
        return accidentDao.selectByCondition(condition);
    }

    @Override
    public Accident queryDetail(Integer id) {
        return accidentDao.selectByPrimaryKey(id);
    }
}
