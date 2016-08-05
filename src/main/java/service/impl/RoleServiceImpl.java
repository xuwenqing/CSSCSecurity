package service.impl;

import dao.ResourceMapper;
import dao.RoleMapper;
import dao.RoleResourceMapper;
import dao.UserRoleMapper;
import dao.condition.RoleCondition;
import model.Role;
import model.RoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.RoleService;

import java.util.LinkedList;
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
    @Autowired
    private UserRoleMapper userRoleDao;

    @Override
    public Role createRole(Role role) {
        if(roleDao.insertSelective(role) == 1)
            return role;
        return null;
    }

    @Override
    public void deleteRole(Long roleId) {
        roleDao.deleteByPrimaryKey(roleId);
        roleResourceDao.deleteManyByRoleId(roleId);
        userRoleDao.deletManyByRoleId(roleId);
    }

    @Override
    public void updateRole(Role role, List<Long> resource_ids) {
        if(role == null)
            return;
        Role old = roleDao.selectByPrimaryKey(role.getId());
        if(old == null)
            return;

        old.setRole(role.getRole());
        old.setDescription(role.getDescription());
        old.setAvailable(role.getAvailable());

        List<Long> delete = roleResourceDao.selectResourceIdsByRoleId(role.getId());
        List<Long> add = new LinkedList<Long>();
        if(delete != null && delete.size() >= 0 && resource_ids != null && resource_ids.size() > 0) {
            for(Long resourceId : resource_ids) {
                if(delete.contains(resourceId)) {
                    delete.remove(resourceId);
                }
                else {
                    add.add(resourceId);
                }
            }
        }
        correlationResources(role.getId(), add);
        uncorrelationResources(role.getId(), delete);
    }

    @Override
    public List<Role> query(RoleCondition condition) {
        return roleDao.selectByCondition(condition);
    }

    @Override
    public int queryCount(RoleCondition condition) {
        return roleDao.selectCountByCondition(condition);
    }

    @Override
    public List<Role> queryAll() {
        return roleDao.selectAll();
    }

    @Override
    public void correlationResources(Long roleId, List<Long> resourceIds) {
        if(roleId == null || resourceIds == null ||  resourceIds.size() == 0) {
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
    public void uncorrelationResources(Long roleId, List<Long> resourceIds) {
        if(roleId == null || resourceIds == null ||  resourceIds.size() == 0) {
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
