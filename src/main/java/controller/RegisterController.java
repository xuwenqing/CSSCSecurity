package controller;

import controller.dto.ResponsePackDto;
import dao.condition.RegisterCondition;
import model.Register;
import model.dto.DataPageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.RegisterService;

import java.util.Date;

/**
 * Created by wenqing on 2016/6/2.
 */
@RequestMapping(value = "/register")
@Controller
public class RegisterController extends BaseController{

    @Autowired
    private RegisterService registerService;

    //{"username":"username1","phone":"18363031040","password":"123456"}
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto register(Register register) {
        System.out.println(register);
        return registerService.register(register);
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public @ResponseBody ResponsePackDto getRegisters() {
        return registerService.getRegisters();
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponsePackDto getRegisters(
          @RequestParam(value = "start", defaultValue = "0") int start,
          @RequestParam(value = "limit", defaultValue = "2") int limit,
          @RequestParam(value = "sortby", required = false, defaultValue = "register_time") String sortby,
          @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
          @RequestParam(value = "startTime", required = false) Date startTime,
          @RequestParam(value = "endTime", required = false) Date endTime) {
        RegisterCondition condition = new RegisterCondition();
        condition.setSortby(sortby);
        condition.setOrder(order);
        condition.setLimit(limit);
        condition.setStart(start);
        DataPageDto pageDto = registerService.getRegisters(condition);
        return new ResponsePackDto(pageDto);
    }

    @RequestMapping(value = "/active", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto activeUser(int id) {
        return registerService.active(id);
    }


}
