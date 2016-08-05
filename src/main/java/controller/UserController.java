package controller;

import controller.dto.*;
import dao.condition.UserCondition;
import model.Role;
import model.User;
import model.UserRole;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RoleService;
import service.UserService;

import java.util.*;

/**
 * Created by wenqing on 2016/5/29.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    protected RoleService roleService;

    @RequiresPermissions("user:create")
    @RequestMapping("/add")
    public
    @ResponseBody
    ResponsePackDto add(@RequestBody User user) {
        ResponsePackDto dto = new ResponsePackDto();
        System.out.println(dto);
        user = userService.createUser(user);
        if (user == null) {
            dto.setStatus(500);
            dto.setError("添加用户失败");
        }
        dto.setData(user);
        return dto;
    }

    @RequiresPermissions("user:delete")
    @RequestMapping("/delete")
    public
    @ResponseBody
    ResponsePackDto delete(@RequestBody LongIdDto id) {
        ResponsePackDto dto = new ResponsePackDto();
        List<Long> ids = new LinkedList<Long>();
        ids.add(id.getId());
        if (!userService.deleteUser(ids)) {
            dto.setStatus(500);
            dto.setError("删除用户失败!");
        }
        return dto;
    }

    @RequiresPermissions("user:delete")
    @RequestMapping("/deletes")
    public
    @ResponseBody
    ResponsePackDto deletes(@RequestBody LongIdsDto ids) {
        ResponsePackDto dto = new ResponsePackDto();
        if (!userService.deleteUser(ids.getIds())) {
            dto.setStatus(500);
            dto.setError("删除用户失败");
        }
        return dto;
    }

    /**
     * 获取所有角色列表
     *
     * @return
     */
    @RequiresPermissions(value={"user:view","user:update","user:create"},logical= Logical.OR)
    @RequestMapping(value = "/getRoles", method = RequestMethod.GET)
    public
    @ResponseBody
    ResponsePackDto getRoles() {
        ResponsePackDto dto = new ResponsePackDto();
        List<Role> roles = roleService.queryAll();
        dto.setData(roles);
        return dto;
    }

    /**
     * 获取某一用户的角色列表
     *
     * @param dto
     * @return
     */
    @RequiresPermissions(value={"user:view","user:update","user:create"},logical= Logical.OR)
    @RequestMapping(value = "/getRoles", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponsePackDto getUserRoles(@RequestBody LongIdDto dto) {
        Set<Role> roles = userService.findRoles(dto.getId());
        List<Role> all = roleService.queryAll();
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("all", all);
        data.put("roles", roles);

        List<UserRoleDto> userRoles = new LinkedList<UserRoleDto>();

        for (Role role : all) {
            UserRoleDto userRoleDto = new UserRoleDto();
            userRoleDto.setId(role.getId());
            userRoleDto.setRole(role.getRole());
            userRoleDto.setDescription(role.getDescription());
            userRoleDto.setAvailable(role.getAvailable());
            if (roles.contains(role)) {
                userRoleDto.setExists(true);
            } else {
                userRoleDto.setExists(false);
            }
            userRoles.add(userRoleDto);
        }

        return new ResponsePackDto(userRoles);
    }

    /**
     * 普通用户编辑，不涉及角色授予
     *
     * @param user
     * @return
     */

    @RequiresPermissions("user:update")
    @RequestMapping("/modify")
    public
    @ResponseBody
    ResponsePackDto edit(@RequestBody User user) {
        ResponsePackDto dto = new ResponsePackDto();
        if (!userService.updateUser(user)) {
            dto.setStatus(500);
            dto.setError("用户信息更新失败");
        }
        return dto;
    }

    /**
     * 用户编辑，涉及角色授予
     *
     * @param userDto
     * @return
     */
    @RequiresPermissions("user:update")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponsePackDto edit(@RequestBody UserDto userDto) {
        ResponsePackDto dto = new ResponsePackDto();
        User user = new User();

        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setSalt(userDto.getSalt());
        user.setLocked(userDto.getLocked());
        user.setDeleted(userDto.getDeleted());
        user.setCreateDate(userDto.getCreateDate());

        if (!userService.updateUser(user, userDto.getRoleIds())) {
            dto.setStatus(500);
            dto.setError("用户角色授予失败");
        }
        return dto;
    }

    @RequiresPermissions("user:view")
    @RequestMapping("/query")
    public
    @ResponseBody
    ResponsePackDto query(@RequestBody(required = false) UserCondition condition) {
        ResponsePackDto dto = new ResponsePackDto();
        if (condition == null)
            condition = new UserCondition();
        if (condition.getSortby() == null)
            condition.setSortby("username");
        dto.setData(userService.query(condition));
        return dto;
    }

    @RequiresPermissions("user:view")
    @RequestMapping(value = "/queryCount", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto queryCount(@RequestBody(required = false) UserCondition condition) {
        if(condition == null)
            condition = new UserCondition();
        int count = userService.queryCount(condition);
        Map<String,Integer> map = new HashMap<String,Integer>(1);
        map.put("count",count);
        return new ResponsePackDto(map);
    }


    /**
     * 修改密码
     *
     * @param userId
     * @param password
     * @return
     */
    @RequiresAuthentication
    @RequestMapping()
    public
    @ResponseBody
    ResponsePackDto modifyPassword(Long userId, String password) {
        userService.changePassword(userId, password);
        return new ResponsePackDto();
    }

}
