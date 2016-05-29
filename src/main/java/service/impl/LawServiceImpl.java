package service.impl;

import dao.LawMapper;
import model.Law;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.LawService;

/**
 * Created by wenqing on 2016/5/29.
 */
@Service("lawService")
public class LawServiceImpl implements LawService {

    @Autowired
    private LawMapper lawDao;

    @Override
    public boolean add(Law law) {
        int id = lawDao.insert(law);
        return true;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Law getById(int id) {
        return null;
    }
}
