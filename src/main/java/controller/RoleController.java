package controller;

import controller.dto.ResponsePackDto;
import controller.dto.RoleDto;
import dao.condition.RoleCondition;
import model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RoleService;

/**
 * Created by wenqing on 2016/6/6.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/add")
    public
    @ResponseBody
    ResponsePackDto add(@RequestBody RoleDto dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setRole(dto.getRole());
        role.setDescription(dto.getDescription());
        role.setAvailable(dto.getAvailable());
        role = roleService.createRole(role);
        roleService.correlationResources(role.getId(), dto.getIds());
        return new ResponsePackDto();
    }

    @RequestMapping("/delete")
    public
    @ResponseBody
    ResponsePackDto delete(Long roleId) {
        roleService.deleteRole(roleId);
        return null;
    }

    @RequestMapping("/edit")
    public
    @ResponseBody
    ResponsePackDto edit(@RequestBody RoleDto dto) {
        Role role = new Role();
        role.setId(dto.getId());
        role.setRole(dto.getRole());
        role.setDescription(dto.getDescription());
        role.setAvailable(dto.getAvailable());
        roleService.updateRole(role, dto.getIds());
        return new ResponsePackDto();
    }

    @RequestMapping("/query")
    public
    @ResponseBody
    ResponsePackDto query(@RequestBody(required = false) RoleCondition condition) {
        ResponsePackDto dto = new ResponsePackDto();
        if (condition == null)
            condition = new RoleCondition();
        if (condition.getSortby() == null) {
            condition.setSortby("role");
        }
        dto.setData(roleService.query(condition));
        return dto;
    }
}
