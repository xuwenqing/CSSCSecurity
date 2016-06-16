package controller;

import controller.dto.ResponsePackDto;
import dao.condition.NewsCondition;
import model.News;
import model.NewsSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.NewsSortService;
import service.NewsService;

import java.util.List;

/**
 * Created by wenqing on 2016/6/6.
 */
@Controller
@RequestMapping(value = "/news")
public class NewsController extends BaseController{

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsSortService newsSortService;


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        return "newsadd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ResponsePackDto add(@RequestBody News news) {
        newsService.add(news);
        return new ResponsePackDto(news);
    }

    @RequestMapping(value = "/delete")
    public @ResponseBody ResponsePackDto delete(List<Integer> ids) {
        newsService.delete(ids);
        return null;
    }



    @RequestMapping(value = "/edit")
    public @ResponseBody ResponsePackDto edit(News oldNews) {
        newsService.edit(oldNews);
        return null;
    }

    @RequestMapping(value = "/query")
    public @ResponseBody ResponsePackDto query(
            @RequestParam(value = "start", defaultValue = "0") int start,
            @RequestParam(value = "limit", defaultValue = "2") int limit,
            @RequestParam(value = "sortby", required = false, defaultValue = "createTime") String sortby,
            @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
            @RequestParam(value = "title",required = false) String title) {
        NewsCondition condition = new NewsCondition();
        condition.setStart(start);
        condition.setLimit(limit);
        condition.setSortby(sortby);
        condition.setOrder(order);
        condition.setTitle(title);
        List<News> news = newsService.query(condition);
        return new ResponsePackDto(news);
    }

    @RequestMapping(value = "/queryDetail")
    public @ResponseBody ResponsePackDto queryDetail(int id) {
        newsService.queryDetail(id);
        return null;
    }

    @RequestMapping(value = "/sort/add")
    public @ResponseBody ResponsePackDto addOrder(NewsSort newsSort) {
        newsSortService.add(newsSort);
        return null;
    }

    @RequestMapping(value = "/sort/delete")
    public @ResponseBody ResponsePackDto deleteOrder(int id) {
        newsSortService.delete(id);
        return null;
    }

    @RequestMapping(value = "/sort/edit")
    public @ResponseBody ResponsePackDto editOrder(NewsSort newsSort) {
        newsSortService.update(newsSort);
        return null;
    }

    @RequestMapping(value = "/sort/query")
    public @ResponseBody ResponsePackDto queryOrder(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "id",required = false) Integer id) {
        NewsSort newsSort = null;
        if(name != null || !name.equals(""))
            newsSort = newsSortService.query(name);
        else if(id != null)
            newsSort = newsSortService.query(id);
        return null;
    }



}
