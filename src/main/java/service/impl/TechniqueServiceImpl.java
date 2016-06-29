package service.impl;

import dao.TechniqueMapper;
import dao.condition.TechniqueCondition;
import model.Technique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.TechniqueService;

import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
@Service
public class TechniqueServiceImpl implements TechniqueService {

    @Autowired
    private TechniqueMapper techniqueDao;

    @Override
    public boolean add(Technique technique) {
        if(techniqueDao.insertSelective(technique) == 1)
            return true;
        return false;
    }

    @Override
    public boolean delete(List<Integer> ids) {
        if(techniqueDao.deleteMany(ids) >= 1)
            return true;
        return false;
    }

    @Override
    public boolean edit(Technique newTechnique) {
        Technique oldTechnique = techniqueDao.selectByPrimaryKey(newTechnique.getId());
        if(oldTechnique == null)
            return false;
        if(techniqueDao.updateByPrimaryKeySelective(newTechnique) == 1)
            return true;
        return false;
    }

    @Override
    public List<Technique> query(TechniqueCondition condition) {
        if(condition.getSortby() == null || "".equals(condition.getSortby()))
            condition.setSortby("publish_date");
        return techniqueDao.selectByCondition(condition);
    }
}
