package controller;

import controller.dto.ResponsePackDto;
import model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ResourceService;

/**
 * Created by wenqing on 2016/6/6.
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {
    @Autowired
    private ResourceService resourceService;

    @RequestMapping("/add")
    public
    @ResponseBody
    ResponsePackDto add(Resource resource) {
        resourceService.createResource(resource);
        return null;
    }

    @RequestMapping("/delete")
    public
    @ResponseBody
    ResponsePackDto delete(Long id) {
        resourceService.deleteResource(id);
        return null;
    }

    @RequestMapping("/edit")
    public
    @ResponseBody
    ResponsePackDto edit(Resource resource) {
        resourceService.updateResource(resource);
        return null;
    }

    @RequestMapping("/query")
    public
    @ResponseBody
    ResponsePackDto query() {
        ResponsePackDto dto = new ResponsePackDto();
        dto.setData(resourceService.query());
        return dto;
    }
}
