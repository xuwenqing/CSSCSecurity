package service;

import model.Role;

import java.util.List;

public interface RoleService {


     Role createRole(Role role);
     void deleteRole(Long roleId);

    /**
     * 添加角色-权限之间关系
     * @param roleId
     * @param resourceIds
     */
     void correlationResources(Long roleId, Long... resourceIds);

     void correlationResourcesL(Long roleId, List<Long> resources);

    /**
     * 移除角色-权限之间关系
     * @param roleId
     * @param resourceIds
     */
     void uncorrelationResources(Long roleId, Long... resourceIds);

}
