package service.impl;

import dao.StandardMapper;
import dao.condition.StandardCondition;
import model.Standard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.StandardService;

import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
@Service
public class StandardServiceImpl implements StandardService {

    @Autowired
    private StandardMapper standardDao;

    @Override
    public boolean add(Standard standard) {

        if(standardDao.insertSelective(standard) == 1)
            return true;
        return false;
    }

    @Override
    public boolean delete(List<Integer> ids) {
        return false;
    }

    @Override
    public boolean edit(Standard newStandard) {
        return false;
    }

    @Override
    public List<Standard> query(StandardCondition condition) {
        return null;
    }

    @Override
    public Standard queryDetail(Integer id) {
        return null;
    }
}
