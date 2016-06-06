package service.impl;

import dao.ResourceMapper;
import model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ResourceService;

/**
 * Created by wenqing on 2016/6/5.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceDao;

    @Override
    public Resource createResource(Resource resource) {
        if(resourceDao.insert(resource) == 1) {
            return resource;
        }
        return null;
    }

    @Override
    public void deleteResource(Long resourceId) {
        resourceDao.deleteByPrimaryKey(resourceId);
    }
}
