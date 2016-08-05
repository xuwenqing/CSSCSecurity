package service;

import dao.condition.RoleCondition;
import model.Role;

import java.util.List;

public interface RoleService {

     Role createRole(Role role);

     void deleteRole(Long roleId);

     void updateRole(Role role,List<Long> resource_ids);

     List<Role> query(RoleCondition condition);

    int queryCount(RoleCondition condition);

     List<Role> queryAll();

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param resourceIds
     */
     void correlationResources(Long roleId, List<Long> resourceIds);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param resourceIds
     */
     void uncorrelationResources(Long roleId, List<Long> resourceIds);

}
