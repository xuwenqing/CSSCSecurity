package service.impl;

import dao.ResourceMapper;
import dao.RoleMapper;
import dao.RoleResourceMapper;
import model.Role;
import model.RoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.RoleService;

import java.util.List;

/**
 * Created by wenqing on 2016/6/5.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleDao;
    @Autowired
    private RoleResourceMapper roleResourceDao;

    @Override
    public Role createRole(Role role) {
        if(roleDao.insertSelective(role) == 1)
            return role;
        return null;
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteByPrimaryKey(roleId);
    }

    @Override
    public void correlationResources(Long roleId, Long... resourceIds) {
        if(roleId == null || resourceIds.length == 0) {
            return;
        }
        for(Long resourceId : resourceIds) {
            RoleResource rr = new RoleResource();
            rr.setRoleId(roleId);
            rr.setResourceId(resourceId);
            roleResourceDao.insertSelective(rr);
        }
    }

    @Override
    public void correlationResourcesL(Long roleId, List<Long> resources) {
        for(Long resourceId : resources) {
            RoleResource rr = new RoleResource();
            rr.setRoleId(roleId);
            rr.setResourceId(resourceId);
            roleResourceDao.insertSelective(rr);
        }
    }

    @Override
    public void uncorrelationResources(Long roleId, Long... resourceIds) {
        if(roleId == null || resourceIds.length == 0) {
            return;
        }
        for(Long resourceId : resourceIds) {
            RoleResource rr = new RoleResource();
            rr.setRoleId(roleId);
            rr.setResourceId(resourceId);
            roleResourceDao.deleteByPrimaryKey(rr);
        }
    }
}
