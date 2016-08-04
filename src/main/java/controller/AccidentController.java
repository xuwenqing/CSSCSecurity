package controller;

import controller.dto.IdDto;
import controller.dto.IdsDto;
import controller.dto.ResponsePackDto;
import dao.condition.AccidentCondition;
import model.Accident;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.AccidentService;
import service.impl.webUploader;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
@Controller
@RequestMapping(value = "/accident")
public class AccidentController extends BaseController {

    @Autowired
    private AccidentService accidentService;

    @Value("${upload.folder}")
    private String uploadFolder;

    private String relativePath = getClass().getClassLoader().getResource("").getPath();;

    @Autowired
    private webUploader wu;

    @RequiresPermissions("accident:create")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto add(@RequestBody Accident Accident) {
        ResponsePackDto dto = new ResponsePackDto();
        if(accidentService.add(Accident))
            return dto;
        else {
            dto.setStatus(500);
            dto.setError("插入数据失败");
        }
        return dto;
    }

    @RequiresPermissions("accident:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto delete(@RequestBody IdDto id) {
        ResponsePackDto dto = new ResponsePackDto();
        List<Integer> ids = new LinkedList<Integer>();
        ids.add(id.getId());
        if(accidentService.delete(ids)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("删除数据失败");
        }
        return dto;
    }

    @RequiresPermissions("accident:delete")
    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto delete(@RequestBody IdsDto ids) {
        ResponsePackDto dto = new ResponsePackDto();
        if(accidentService.delete(ids.getIds())) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("删除数据失败");
        }
        return dto;
    }

    @RequiresPermissions("accident:update")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto edit(@RequestBody Accident newAccident) {

        List<String> dels = newAccident.getDels();

        if(dels != null && dels.size() > 0) {
            for(String name : dels)
                wu.deleteFolder(name,relativePath + uploadFolder);
        }

        ResponsePackDto dto = new ResponsePackDto();
        if(accidentService.edit(newAccident)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("修改数据失败");
        }
        return dto;
    }

    @RequiresPermissions("accident:view")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto query(@RequestBody(required = false) AccidentCondition condition) {
        if(condition == null)
            condition = new AccidentCondition();
        List<Accident> Accidents = accidentService.query(condition);
        return new ResponsePackDto(Accidents);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto queryDetail(@RequestBody IdDto id) {
        return new ResponsePackDto(accidentService.queryDetail(id.getId()));
    }

}
