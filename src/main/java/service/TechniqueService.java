package service;

import dao.condition.TechniqueCondition;
import model.Technique;

import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
public interface TechniqueService {
    boolean add(Technique technique);
    boolean delete(List<Integer> ids);
    boolean edit(Technique newTechnique);
    List<Technique> query(TechniqueCondition condition);
}
