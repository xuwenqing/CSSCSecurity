package service.impl;

import dao.ResourceMapper;
import dao.RoleResourceMapper;
import model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ResourceService;

import java.io.PrintWriter;
import java.util.List;

/**
 * Created by wenqing on 2016/6/5.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceDao;
    @Autowired
    private RoleResourceMapper roleResourceDao;

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
        roleResourceDao.deleteManyByResourceId(resourceId);
    }

    @Override
    public void updateResource(Resource resource) {
        if(resource == null)
            return;
        Resource old = resourceDao.selectByPrimaryKey(resource.getId());
        if(old == null)
            return;

        old.setName(resource.getName());
        old.setType(resource.getType());
        old.setParentId(resource.getParentId());
        old.setParentIds(resource.getParentIds());
        old.setUrl(resource.getUrl());
        old.setPermission(resource.getPermission());
        old.setAvailable(resource.getAvailable());

        resourceDao.updateByPrimaryKeySelective(old);
    }

    @Override
    public List<Resource> query() {
        return resourceDao.selectAll();
    }

    @Override
    public List<Resource> queryByRoleId(Long id) {
        return resourceDao.selectByRoleId(id);
    }
}
