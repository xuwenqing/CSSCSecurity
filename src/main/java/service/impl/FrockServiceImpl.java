package service.impl;

import com.alibaba.fastjson.JSON;
import controller.dto.FileDto;
import dao.FrockMapper;
import dao.condition.FrockCondition;
import model.Frock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.FrockService;

import java.util.List;
import java.util.Objects;

/**
 * Created by wenqing on 2016/6/29.
 */
@Service
public class FrockServiceImpl implements FrockService {

    @Autowired
    private FrockMapper frockDao;

    @Value("${upload.folder}")
    private String uploadFolder;

    private String relativePath = getClass().getClassLoader().getResource("").getPath();;

    @Autowired
    private webUploader wu;

    @Override
    public boolean add(Frock frock) {
        if(frockDao.insertSelective(frock) == 1)
            return true;
        return false;
    }

    @Override
    public boolean delete(List<Integer> ids) {

        if(ids != null || !ids.isEmpty()) {
            for(Integer id : ids) {
                Frock frock = frockDao.selectByPrimaryKey(id);
                if(frock != null && !Objects.equals(frock.getFile(), "")) {
                    List<FileDto> fileDtos = JSON.parseArray(frock.getFile(), FileDto.class);
                    for(FileDto fileDto : fileDtos) {
                        wu.deleteFolder(fileDto.getFilepath(), relativePath + uploadFolder);
                    }
                }
            }
        }

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
