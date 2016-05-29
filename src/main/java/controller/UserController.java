package controller;

import controller.dto.ResponsePackDto;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;

/**
 * Created by wenqing on 2016/5/29.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public @ResponseBody ResponsePackDto
            query(@RequestParam(value = "id") Integer id) {
        User user = userService.getUserById(id);
        return new ResponsePackDto(user);
    }
}
