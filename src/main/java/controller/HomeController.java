package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wenqing on 2016/5/22.
 */
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController{

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "hello";
    }

}
