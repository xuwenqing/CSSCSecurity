package wechat.controller;

import org.junit.Test;
import wechat.api.BaseAPI;
import wechat.api.MenuAPI;
import wechat.bean.BaseResult;
import wechat.bean.menu.Button;
import wechat.bean.menu.Menu;
import wechat.bean.menu.MenuButtons;
import wechat.bean.token.Token;
import wechat.util.JsonUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by wenqing on 2016/5/22.
 */
public class MenuAPITest {

    public static String access_token = "ruoHbmuXnz8NgjhFVcrBihobsiRuwblATO7g2-O0oFkhV2BYPtW-UWQzW6Ofp1IL7CU_fzbZZ0JCneVk5g9rCnFZnTIWo-vcUvQBkYXuRohlLlJcoLFGjKETic_fMuTfXPReAJAJBD";

    @Test
    public void createMenuByMenuButtons() {
        MenuButtons menuButtons = new MenuButtons();

        Button[] buttons = new Button[3];

        buttons[0] = new Button();
        buttons[0].setType("click");
        buttons[0].setKey("key1");
        buttons[0].setName("点击菜单");

        buttons[1] = new Button();
        List<Button> subBtn = new LinkedList<Button>();
        Button sub1 = new Button();
        sub1.setName("百度一下");
        sub1.setType("view");
        sub1.setUrl("https://www.baidu.com");

        Button sub2 = new Button();
        sub2.setName("赞一下");
        sub2.setKey("key2");
        sub2.setType("click");

        subBtn.add(sub1);
        subBtn.add(sub2);
        buttons[1].setName("多级菜单");
        buttons[1].setSub_button(subBtn);

        buttons[2] = new Button();
        buttons[2].setType("view");
        buttons[2].setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx21d417fb548fdf71&redirect_uri=http%3A%2F%2Fxxd763955430.eicp.net%2Fwechat%2Flogin&response_type=code&scope=snsapi_userinfo&state=#wechat_redirect");
        buttons[2].setName("个人中心");

        menuButtons.setButton(buttons);

        String json =JsonUtil.toJSONString(menuButtons);

        System.out.println(json);


        BaseResult result = MenuAPI.menuCreate(access_token, menuButtons);
        System.out.println(result.getErrmsg());
        System.out.println(result.getErrcode());

    }

    @Test
    public void createMenuByJSON() {
        String menu = "{\n" +
                "        \"button\":[\n" +
                "        {\n" +
                "            \"type\":\"click\",\n" +
                "                \"name\":\"今日歌曲\",\n" +
                "                \"key\":\"V1001_TODAY_MUSIC\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\":\"菜单\",\n" +
                "                \"sub_button\":[\n" +
                "            {\n" +
                "                \"type\":\"view\",\n" +
                "                    \"name\":\"搜索\",\n" +
                "                    \"url\":\"http://www.soso.com/\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"type\":\"view\",\n" +
                "                    \"name\":\"视频\",\n" +
                "                    \"url\":\"http://v.qq.com/\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"type\":\"click\",\n" +
                "                    \"name\":\"赞一下我们\",\n" +
                "                    \"key\":\"V1001_GOOD\"\n" +
                "            }]\n" +
                "        }]\n" +
                "    }";
        BaseResult result = MenuAPI.menuCreate(access_token, menu);
        System.out.println(result.getErrmsg());
        System.out.println(result.getErrcode());

    }

    @Test
    public void deleteMenu() {
        BaseResult result = MenuAPI.menuDelete(access_token);
        System.out.println(result.getErrmsg());
        System.out.println(result.getErrcode());

    }

    @Test
    public void getMenu() {
        Menu menu = MenuAPI.menuGet(access_token);
        System.out.println(menu);
    }


}
