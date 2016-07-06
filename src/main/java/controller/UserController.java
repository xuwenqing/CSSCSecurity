package controller;

import controller.dto.LongIdDto;
import controller.dto.LongIdsDto;
import controller.dto.ResponsePackDto;
import controller.dto.UserUpdateDto;
import dao.condition.UserCondition;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wenqing on 2016/5/29.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

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

    @RequestMapping("/deletes")
    public
    @ResponseBody
    ResponsePackDto delete(@RequestBody LongIdDto id) {
        ResponsePackDto dto = new ResponsePackDto();
        List<Long> ids = new LinkedList<Long>();
        ids.add(id.getId());
        if (!userService.deleteUser(ids)) {
            dto.setStatus(500);
            dto.setError("删除用户失败");
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

    @RequestMapping("/edit")
    public
    @ResponseBody
    ResponsePackDto edit(@RequestBody UserUpdateDto userDto) {
        ResponsePackDto dto = new ResponsePackDto();
        if(!userService.updateUser(userDto.getUser(), userDto.getRoleIds())) {
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
