package controller;

import controller.dto.ResponsePackDto;
import dao.condition.StandardCondition;
import model.Standard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.StandardService;

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
        ResponsePackDto dto = new ResponsePackDto();
        if(standardService.add(standard))
            return dto;
        else {
            dto.setStatus(500);
            dto.setError("≤Â»Î ˝æ› ß∞‹");
        }
        return dto;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto delete(Integer id) {
        return null;
    }

    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto delete(List<Integer> ids) {
        return null;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto edit(@RequestBody Standard newStandard) {
        return null;
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto query(@RequestBody StandardCondition condition) {
        System.out.println(condition);
        return new ResponsePackDto(condition);
    }

//    @RequestMapping(value = "/query", method = RequestMethod.POST)
//    public @ResponseBody ResponsePackDto queryquery(
//            @RequestParam(value = "start", defaultValue = "0") int start,
//            @RequestParam(value = "limit", defaultValue = "2") int limit,
//            @RequestParam(value = "sortby", required = false, defaultValue = "publishDate") String sortby,
//            @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
//            @RequestParam(value = "name",required = false) String name,
//            @RequestParam(value = "keywords",required = false) String keywords) {
//
//        StandardCondition condition = new StandardCondition();
//        condition.setStart(start);
//        condition.setLimit(limit);
//        condition.setSortby(sortby);
//        condition.setOrder(order);
//        if(name != null)
//            condition.setName(name);
//        if(keywords != null)
//            condition.setKeywords(keywords);
//        return new ResponsePackDto(condition);
//    }



}
