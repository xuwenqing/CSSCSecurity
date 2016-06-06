package controller;

import controller.dto.ResponsePackDto;
import dao.condition.RoleCondition;
import model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RoleService;

import java.util.List;

/**
 * Created by wenqing on 2016/6/6.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/add")
    public @ResponseBody ResponsePackDto add(Role role,List<Long> resource_ids) {
        role = roleService.createRole(role);
        roleService.correlationResources(role.getId(),resource_ids);
        return null;
    }

    @RequestMapping("/delete")
    public @ResponseBody ResponsePackDto delete(Long roleId) {
        roleService.deleteRole(roleId);
        return null;
    }

    @RequestMapping("/edit")
    public @ResponseBody ResponsePackDto edit(Role role,List<Long> resource_ids) {
        roleService.updateRole(role, resource_ids);
        return null;
    }

    @RequestMapping("/query")
    public @ResponseBody ResponsePackDto query(RoleCondition condition) {
        roleService.query(condition);
        return null;
    }
}
