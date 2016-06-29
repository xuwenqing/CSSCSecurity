package controller;

import controller.dto.ResponsePackDto;
import dao.condition.TechniqueCondition;
import model.Technique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.TechniqueService;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wenqing on 2016/6/29.
 */
@Controller
@RequestMapping(value = "/technique")
public class TechniqueController extends BaseController {

    @Autowired
    private TechniqueService techniqueService;

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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto delete(Integer id) {
        ResponsePackDto dto = new ResponsePackDto();
        List<Integer> ids = new LinkedList<Integer>();
        ids.add(id);
        if(techniqueService.delete(ids)) {
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
        if(techniqueService.delete(ids)) {
            return dto;
        }
        else {
            dto.setStatus(500);
            dto.setError("删除数据失败");
        }
        return dto;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto edit(@RequestBody Technique newTechnique) {
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

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto query(@RequestBody TechniqueCondition condition) {
        List<Technique> Techniques = techniqueService.query(condition);
        return new ResponsePackDto(Techniques);
    }

}
