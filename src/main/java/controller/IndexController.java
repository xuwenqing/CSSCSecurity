package controller;

import controller.dto.ResponsePackDto;
import model.Resource;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ResourceService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Set;

/**
 * Created by wenqing on 2016/5/22.
 */
@Controller
public class IndexController {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public
    //@ResponseBody ResponsePackDto
    String index(HttpServletRequest req, Model model) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        if(username == null || Objects.equals(username,"")) {
            return "redirect:/admin/login.html";
        }
        else {
            return "redirect:/admin/index.html";
        }
//        Set<Resource> resources = userService.findResources(username);
//        model.addAttribute("resources", resources);
//        request.setAttribute(Constants.CURRENT_USER, userService.findByUsername(username));
//        String username = (String)req.getAttribute("username");
//        Set<String> permissions = userService.findPermissions(username);
//        List<Resource> menus = permissionService.findMenus(permissions);
//        model.addAttribute("menus", menus);
//        return "index";
        //req.getRequestDispatcher().
        //return new ResponsePackDto(resources);
    }
}