package service.impl;

import com.alibaba.fastjson.JSON;
import controller.dto.FileDto;
import dao.AccidentMapper;
import dao.condition.AccidentCondition;
import model.Accident;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.AccidentService;

import java.util.List;
import java.util.Objects;

/**
 * Created by wenqing on 2016/6/29.
 */
@Service
public class AccidentServiceImpl implements AccidentService {
    @Autowired
    private AccidentMapper accidentDao;

    @Value("${upload.folder}")
    private String uploadFolder;

    private String relativePath = getClass().getClassLoader().getResource("").getPath();;

    @Autowired
    private webUploader wu;

    @Override
    public boolean add(Accident accident) {
        if(accidentDao.insertSelective(accident) == 1)
            return true;
        return false;
    }

    @Override
    public boolean delete(List<Integer> ids) {

        if(ids != null || !ids.isEmpty()) {
            for(Integer id : ids) {
                Accident accident = accidentDao.selectByPrimaryKey(id);
                if(accident != null && !Objects.equals(accident.getFile(), "")) {
                    List<FileDto> fileDtos = JSON.parseArray(accident.getFile(), FileDto.class);
                    for(FileDto fileDto : fileDtos) {
                        wu.deleteFolder(fileDto.getFilepath(), relativePath + uploadFolder);
                    }
                }
            }
        }

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
    public int queryCount(AccidentCondition condition) {
        return accidentDao.selectCountByCondition(condition);
    }

    @Override
    public Accident queryDetail(Integer id) {
        return accidentDao.selectByPrimaryKey(id);
    }
}
