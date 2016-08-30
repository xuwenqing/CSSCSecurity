package controller;

import controller.dto.IdDto;
import controller.dto.IdsDto;
import controller.dto.ResponsePackDto;
import dao.condition.LawCondition;
import model.Law;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.LawService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by wenqing on 2016/6/29.
 */
@Controller
@RequestMapping(value = "/law")
public class LawController extends BaseController {

    @Autowired
    private LawService lawService;

    @RequiresPermissions("law:create")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponsePackDto add(@RequestBody Law law) {
        ResponsePackDto dto = new ResponsePackDto();
        if (lawService.add(law))
            return dto;
        else {
            dto.setStatus(500);
            dto.setError("插入数据失败");
        }
        return dto;
    }

    @RequiresPermissions("law:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponsePackDto delete(@RequestBody IdDto id) {
        ResponsePackDto dto = new ResponsePackDto();
        List<Integer> ids = new LinkedList<Integer>();
        ids.add(id.getId());
        if (lawService.delete(ids)) {
            return dto;
        } else {
            dto.setStatus(500);
            dto.setError("删除数据失败");
        }
        return dto;
    }

    @RequiresPermissions("law:delete")
    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponsePackDto delete(@RequestBody IdsDto ids) {
        ResponsePackDto dto = new ResponsePackDto();
        if (lawService.delete(ids.getIds())) {
            return dto;
        } else {
            dto.setStatus(500);
            dto.setError("删除数据失败");
        }
        return dto;
    }

    @RequiresPermissions("law:update")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponsePackDto edit(@RequestBody Law newLaw) {
        ResponsePackDto dto = new ResponsePackDto();
        if (lawService.edit(newLaw)) {
            return dto;
        } else {
            dto.setStatus(500);
            dto.setError("修改数据失败");
        }
        return dto;
    }

    //@RequiresPermissions("law:view")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponsePackDto query(@RequestBody(required = false) LawCondition condition) {
        if (condition == null)
            condition = new LawCondition();
        List<Law> Laws = lawService.query(condition);
        return new ResponsePackDto(Laws);
    }

    //@RequiresPermissions("law:view")
    @RequestMapping(value = "/queryCount", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto queryCount(@RequestBody(required = false) LawCondition condition) {
        if(condition == null)
            condition = new LawCondition();
        int count = lawService.queryCount(condition);
        Map<String,Integer> map = new HashMap<String,Integer>(1);
        map.put("count",count);
        return new ResponsePackDto(map);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponsePackDto queryDetail(@RequestBody IdDto id) {
        return new ResponsePackDto(lawService.queryDetail(id.getId()));
    }

}
