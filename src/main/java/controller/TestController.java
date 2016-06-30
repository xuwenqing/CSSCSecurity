package controller;

import controller.dto.ResponsePackDto;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wenqing on 2016/6/29.
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    //@RequiresPermissions(value={"xxx:xxx","xxx:xxx"},logical=Logical.OR)，多权限任选一参数
    @RequiresPermissions("user:create")
    @RequestMapping("/permission")
    public @ResponseBody
    ResponsePackDto permission() {
        System.out.print("test - permission");
        return new ResponsePackDto("permission");
    }

    @RequiresRoles("admin")
    @RequestMapping("/role")
    public @ResponseBody
    ResponsePackDto role() {
        System.out.print("test - role");
        return new ResponsePackDto("role");
    }

    @RequestMapping("/exception")
    public @ResponseBody
    ResponsePackDto exception() {
        System.out.print("test - exception");
        int a = 1/0;
        return new ResponsePackDto("exception");
    }

    @RequiresPermissions("wenqing:xu")
    @RequestMapping("/unauthorized")
    public @ResponseBody
    ResponsePackDto unauthorized() {
        System.out.print("test - unauthorized");
        return new ResponsePackDto("unauthorized");
    }

    @RequestMapping(value = "/normal",method = RequestMethod.POST)
    public @ResponseBody
    ResponsePackDto normal() {
        System.out.print("test - normal");
        return new ResponsePackDto("normal");
    }
}
