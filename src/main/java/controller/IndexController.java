package controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import service.ResourceService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wenqing on 2016/5/22.
 */
@Controller
public class IndexController {

    @Autowired
    private ResourceService permissionService;
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(HttpServletRequest req, Model model) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
        //String username = (String)req.getAttribute("username");
        //       Set<String> permissions = userService.findPermissions(username);
//        List<Resource> menus = permissionService.findMenus(permissions);
//        model.addAttribute("menus", menus);
        return "index";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}