package controller;

import controller.dto.IdDto;
import controller.dto.ResponsePackDto;
import dao.condition.StandardCondition;
import model.Standard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.StandardService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
@Controller
@RequestMapping(value = "/standard")
public class StandardController extends BaseController {

    @Autowired
    private StandardService standardService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto add(@RequestBody Standard standard) {
        System.out.println(standard);
        ResponsePackDto dto = new ResponsePackDto();
        if(standardService.add(standard))
            return dto;
        else {
            dto.setStatus(500);
            dto.setError("插入数据失败");
        }
        return dto;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto delete(@RequestBody IdDto id) {
        System.out.println(id);
        ResponsePackDto dto = new ResponsePackDto();
        List<Integer> ids = new LinkedList<Integer>();
        ids.add(id.getId());
        if(standardService.delete(ids)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("删除数据失败");
        }
        return dto;
    }

    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto delete(List<Integer> ids) {
        ResponsePackDto dto = new ResponsePackDto();
        if(standardService.delete(ids)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("删除数据失败");
        }
        return dto;
    }

    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto edit(@RequestBody IdDto id) {
        return new ResponsePackDto(standardService.queryDetail(id.getId()));
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto edit(@RequestBody Standard newStandard) {
        ResponsePackDto dto = new ResponsePackDto();
        if(standardService.edit(newStandard)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("修改数据失败");
        }
        return dto;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto query(@RequestBody(required = false) StandardCondition condition) {
        if(condition == null)
            condition = new StandardCondition();
        List<Standard> standards = standardService.query(condition);
        return new ResponsePackDto(standards);
    }

}
