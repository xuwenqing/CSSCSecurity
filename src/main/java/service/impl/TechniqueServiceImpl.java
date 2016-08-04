package service.impl;

import com.alibaba.fastjson.JSON;
import controller.dto.FileDto;
import dao.TechniqueMapper;
import dao.condition.TechniqueCondition;
import model.Technique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.TechniqueService;

import java.util.List;
import java.util.Objects;

/**
 * Created by wenqing on 2016/6/29.
 */
@Service
public class TechniqueServiceImpl implements TechniqueService {

    @Autowired
    private TechniqueMapper techniqueDao;

    @Value("${upload.folder}")
    private String uploadFolder;

    private String relativePath = getClass().getClassLoader().getResource("").getPath();;

    @Autowired
    private webUploader wu;

    @Override
    public boolean add(Technique technique) {
        if(techniqueDao.insertSelective(technique) == 1)
            return true;
        return false;
    }

    @Override
    public boolean delete(List<Integer> ids) {

        if(ids != null || !ids.isEmpty()) {
            for(Integer id : ids) {
                Technique technique = techniqueDao.selectByPrimaryKey(id);
                if(technique != null && !Objects.equals(technique.getFile(), "")) {
                    List<FileDto> fileDtos = JSON.parseArray(technique.getFile(), FileDto.class);
                    for(FileDto fileDto : fileDtos) {
                        wu.deleteFolder(fileDto.getFilepath(), relativePath + uploadFolder);
                    }
                }
            }
        }

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

    @Override
    public Technique queryDetail(Integer id) {
        return techniqueDao.selectByPrimaryKey(id);
    }
}
