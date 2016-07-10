package controller;

import controller.dto.LongIdDto;
import controller.dto.LongIdsDto;
import controller.dto.ResponsePackDto;
import controller.dto.UserDto;
import dao.condition.UserCondition;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.RoleService;
import service.UserService;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    @RequestMapping("/add")
    public
    @ResponseBody
    ResponsePackDto add(@RequestBody User user) {
        ResponsePackDto dto = new ResponsePackDto();
        user = userService.createUser(user);
        if (user == null) {
            dto.setStatus(500);
            dto.setError("添加用户失败");
        }
        dto.setData(user);
        return dto;
    }

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

    @RequestMapping("/modify")
    public
    @ResponseBody
    ResponsePackDto edit(@RequestBody User user) {
        ResponsePackDto dto = new ResponsePackDto();
        if(!userService.updateUser(user)) {
            dto.setStatus(500);
            dto.setError("用户信息更新失败");
        }
        return dto;
    }

    @RequestMapping(value = "/getRoles")
    public
    @ResponseBody
    ResponsePackDto getRoles() {
        ResponsePackDto dto = new ResponsePackDto();
        List<Role> roles = roleService.queryAll();
        dto.setData(roles);
        return dto;
    }

    @RequestMapping(value = "/getRoles",method = RequestMethod.POST)
    public
    @ResponseBody
    ResponsePackDto queryUserRoles(@RequestBody LongIdDto dto) {
        Set<Role> roles = userService.findRoles(dto.getId());
        return new ResponsePackDto(roles);
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
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

        if(!userService.updateUser(user, userDto.getRoleIds())) {
            dto.setStatus(500);
            dto.setError("用户角色授予失败");
        }
        return dto;
    }

    @RequestMapping("/query")
    public
    @ResponseBody
    ResponsePackDto query(@RequestBody(required = false) UserCondition condition) {
        ResponsePackDto dto = new ResponsePackDto();
        if(condition == null)
            condition = new UserCondition();
        if(condition.getSortby() == null)
            condition.setSortby("username");
        dto.setData(userService.query(condition));
        return dto;
    }

    @RequestMapping()
    public
    @ResponseBody
    ResponsePackDto modifyPassword(Long userId, String password) {
        userService.changePassword(userId, password);
        return new ResponsePackDto();
    }

}
