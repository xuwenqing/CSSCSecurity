package wechat.controller;

import org.junit.Test;
import wechat.api.UserAPI;
import wechat.bean.user.User;

/**
 * Created by wenqing on 2016/5/23.
 */
public class UserAPITest {
    public static String access_token = "ruoHbmuXnz8NgjhFVcrBihobsiRuwblATO7g2-O0oFkhV2BYPtW-UWQzW6Ofp1IL7CU_fzbZZ0JCneVk5g9rCnFZnTIWo-vcUvQBkYXuRohlLlJcoLFGjKETic_fMuTfXPReAJAJBD";

    @Test
    public void getUserInfo() {
        String openId = "oeTbiv9jH5pIak6uUbMq6xAF6r_8";

        User user = UserAPI.userInfo(access_token, openId);
        System.out.println(user);

    }
}
