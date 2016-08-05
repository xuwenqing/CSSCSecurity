package controller;

import controller.dto.IdDto;
import controller.dto.IdsDto;
import controller.dto.ResponsePackDto;
import dao.condition.HealthCondition;
import model.Health;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.HealthService;
import service.impl.webUploader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by wenqing on 2016/6/29.
 */
@Controller
@RequestMapping(value = "/health")
public class HealthController extends BaseController {

    @Autowired
    private HealthService healthService;

    @Value("${upload.folder}")
    private String uploadFolder;

    private String relativePath = getClass().getClassLoader().getResource("").getPath();;

    @Autowired
    private webUploader wu;

    @RequiresPermissions("health:create")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto add(@RequestBody Health Health) {

        System.out.println(Health);

        ResponsePackDto dto = new ResponsePackDto();
        if(healthService.add(Health))
            return dto;
        else {
            dto.setStatus(500);
            dto.setError("插入数据失败");
        }
        return dto;
    }

    @RequiresPermissions("health:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto delete(@RequestBody IdDto id) {
        ResponsePackDto dto = new ResponsePackDto();
        List<Integer> ids = new LinkedList<Integer>();
        ids.add(id.getId());
        if(healthService.delete(ids)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("删除数据失败");
        }
        return dto;
    }

    @RequiresPermissions("health:delete")
    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto delete(@RequestBody IdsDto ids) {
        ResponsePackDto dto = new ResponsePackDto();
        if(healthService.delete(ids.getIds())) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("删除数据失败");
        }
        return dto;
    }

    @RequiresPermissions("health:update")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto edit(@RequestBody Health newHealth) {

        List<String> dels = newHealth.getDels();

        if(dels != null && dels.size() > 0) {
            for(String name : dels)
                wu.deleteFolder(name,relativePath + uploadFolder);
        }

        ResponsePackDto dto = new ResponsePackDto();
        if(healthService.edit(newHealth)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("修改数据失败");
        }
        return dto;
    }

    @RequiresPermissions("health:view")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto query(@RequestBody(required = false) HealthCondition condition) {
        if(condition == null)
            condition = new HealthCondition();
        List<Health> Healths = healthService.query(condition);
        return new ResponsePackDto(Healths);
    }

    @RequiresPermissions("health:view")
    @RequestMapping(value = "/queryCount", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto queryCount(@RequestBody(required = false) HealthCondition condition) {
        if(condition == null)
            condition = new HealthCondition();
        int count = healthService.queryCount(condition);
        Map<String,Integer> map = new HashMap<String,Integer>(1);
        map.put("count",count);
        return new ResponsePackDto(map);
    }

    @RequiresAuthentication
    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto queryDetail(@RequestBody IdDto id) {
        return new ResponsePackDto(healthService.queryDetail(id.getId()));
    }

}
