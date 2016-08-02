package controller;

import controller.dto.ResponsePackDto;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wenqing on 2016/6/5.
 */
@Controller
public class LoginController extends BaseController {

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto  loginFailed(HttpServletRequest req) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        ResponsePackDto dto = new ResponsePackDto();
        dto.setStatus(500);
        dto.setError(error);
        return dto;
    }

    @RequiresAuthentication
    @RequestMapping("/login/success")
    public @ResponseBody
    ResponsePackDto index(HttpServletRequest req, Model model) {

        return new ResponsePackDto();
    }

}
