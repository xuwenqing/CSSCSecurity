package controller;

import controller.dto.ResponsePackDto;
import controller.enumerate.ResponseStatusEnum;
import dao.LawMapper;
import javafx.beans.binding.ObjectBinding;
import model.Law;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wenqing on 2016/5/26.
 */
@Controller
@RequestMapping(value = "/law")
public class LawController extends BaseController {

//    ?limit=10：指定返回记录的数量
//    ?offset=10：指定返回记录的开始位置。
//    ?page=2&per_page=100：指定第几页，以及每页的记录数。
//    ?sortby=name&order=asc：指定返回结果按照哪个属性排序，以及排序顺序。
//    ?animal_type_id=1：指定筛选条件

    @Autowired
    private LawMapper lawMapper;

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto add(@RequestBody Law law) {
        int result = lawMapper.insert(law);
        ResponsePackDto response = new ResponsePackDto();
        if (result == 1) {//插入成功

        }
        else { //插入失败
            response.setStatus(ResponseStatusEnum.SERVER_ERROR.value());
        }
        return response;
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public @ResponseBody ResponsePackDto delete(@RequestParam int id) {
        return null;
    }

    //
    @RequestMapping(method = RequestMethod.PUT)
    public @ResponseBody ResponsePackDto edit(@RequestBody Law law) {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody ResponsePackDto query(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            @RequestParam(value = "sortby", required = false, defaultValue = "publishtime") String sortby,
            @RequestParam(value = "order", required = false, defaultValue = "descs") String order,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "publish_institution", required = false)String publish_institution) {
        return null;
    }

}
