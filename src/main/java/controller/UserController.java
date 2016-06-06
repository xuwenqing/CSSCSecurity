package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;

/**
 * Created by wenqing on 2016/5/29.
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public @ResponseBody ResponsePackDto
//            query(@RequestParam(value = "id") Integer id) {
//        User user = userService.getUserById(id);
//        return new ResponsePackDto(user);
//    }
}
