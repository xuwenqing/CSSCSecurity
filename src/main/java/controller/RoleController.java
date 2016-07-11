package controller;

import controller.dto.LongIdDto;
import controller.dto.ResponsePackDto;
import controller.dto.RoleDto;
import dao.condition.RoleCondition;
import model.Resource;
import model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ResourceService;
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

    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/add")
    public
    @ResponseBody
    ResponsePackDto add(@RequestBody Role role) {
//        Role role = new Role();
//        role.setId(dto.getId());
//        role.setRole(dto.getRole());
//        role.setDescription(dto.getDescription());
//        role.setAvailable(dto.getAvailable());
//        roleService.correlationResources(role.getId(), dto.getIds());
        ResponsePackDto dto = new ResponsePackDto();
        role = roleService.createRole(role);
        if(role == null) {
            dto.setStatus(500);
            dto.setError("角色信息更新失败");
        }
        return dto;
    }

    @RequestMapping("/delete")
    public
    @ResponseBody
    ResponsePackDto delete(@RequestBody LongIdDto roleId) {
        roleService.deleteRole(roleId.getId());
        return null;
    }

    /**
     * 获取所有权限列表
     * @return
     */
    @RequestMapping(value = "/getResources",method = RequestMethod.GET)
    public
    @ResponseBody
    ResponsePackDto getResources() {
        ResponsePackDto dto = new ResponsePackDto();
        List<Resource> roles = resourceService.query();
        dto.setData(roles);
        return dto;
    }

    /**
     * 获取某一角色的权限列表
     * @return
     */
    @RequestMapping(value = "/getResources",method = RequestMethod.POST)
    public
    @ResponseBody
    ResponsePackDto getResources(@RequestBody LongIdDto idDto) {
        ResponsePackDto dto = new ResponsePackDto();
        List<Resource> roles = resourceService.query();
        dto.setData(roles);
        return dto;
    }

    /**
     *
     * @param dto
     * @return
     */
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
