package controller;

import controller.dto.ResponsePackDto;
import dao.condition.UserCondition;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

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
    public @ResponseBody
    ResponsePackDto add(User user) {
        userService.createUser(user);
        return null;
    }

    @RequestMapping("/delete")
    public @ResponseBody ResponsePackDto delete(List<Long> user_ids) {
        userService.deleteUser(user_ids);
        return null;
    }

    @RequestMapping("/edit")
    public @ResponseBody ResponsePackDto edit(User user,List<Long > resources) {
        userService.updateUser(user, resources);
        return null;
    }

    @RequestMapping("/query")
    public @ResponseBody ResponsePackDto query(UserCondition condition) {
        userService.query(condition);
        return null;
    }

    @RequestMapping()
    public @ResponseBody ResponsePackDto modifyPassword(Long userId,String password){
        userService.changePassword(userId,password);
        return null;
    }

}
