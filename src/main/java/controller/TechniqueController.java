package controller;

import controller.dto.IdDto;
import controller.dto.IdsDto;
import controller.dto.ResponsePackDto;
import dao.condition.TechniqueCondition;
import model.Technique;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.TechniqueService;
import service.impl.webUploader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by wenqing on 2016/6/29.
 */
@Controller
@RequestMapping(value = "/technique")
public class TechniqueController extends BaseController {

    @Autowired
    private TechniqueService techniqueService;

    @Value("${upload.folder}")
    private String uploadFolder;

    private String relativePath = getClass().getClassLoader().getResource("").getPath();;

    @Autowired
    private webUploader wu;

    @RequiresPermissions("technique:create")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto add(@RequestBody Technique Technique) {
        ResponsePackDto dto = new ResponsePackDto();
        if(techniqueService.add(Technique))
            return dto;
        else {
            dto.setStatus(500);
            dto.setError("插入数据失败");
        }
        return dto;
    }

    @RequiresPermissions("technique:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto delete(@RequestBody IdDto id) {
        ResponsePackDto dto = new ResponsePackDto();
        List<Integer> ids = new LinkedList<Integer>();
        ids.add(id.getId());
        if(techniqueService.delete(ids)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("删除数据失败");
        }
        return dto;
    }

    @RequiresPermissions("technique:delete")
    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto delete(@RequestBody IdsDto ids) {
        ResponsePackDto dto = new ResponsePackDto();
        if(techniqueService.delete(ids.getIds())) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("删除数据失败");
        }
        return dto;
    }

    @RequiresPermissions("technique:update")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto edit(@RequestBody Technique newTechnique) {

        List<String> dels = newTechnique.getDels();

        if(dels != null && dels.size() > 0) {
            for(String name : dels)
                wu.deleteFolder(name,relativePath + uploadFolder);
        }

        ResponsePackDto dto = new ResponsePackDto();
        if(techniqueService.edit(newTechnique)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("修改数据失败");
        }
        return dto;
    }

    //@RequiresPermissions("technique:view")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto query(@RequestBody(required = false) TechniqueCondition condition) {
        if(condition == null)
            condition = new TechniqueCondition();
        List<Technique> Techniques = techniqueService.query(condition);
        return new ResponsePackDto(Techniques);
    }

    //@RequiresPermissions("technique:view")
    @RequestMapping(value = "/queryCount", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto queryCount(@RequestBody(required = false) TechniqueCondition condition) {
        if(condition == null)
            condition = new TechniqueCondition();
        int count = techniqueService.queryCount(condition);
        Map<String,Integer> map = new HashMap<String,Integer>(1);
        map.put("count",count);
        return new ResponsePackDto(map);
    }

    //@RequiresAuthentication
    @RequestMapping(value = "/queryDetail", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto queryDetail(@RequestBody IdDto id) {
        return new ResponsePackDto(techniqueService.queryDetail(id.getId()));
    }
}
