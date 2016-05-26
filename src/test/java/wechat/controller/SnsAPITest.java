package wechat.controller;

import org.junit.Test;
import wechat.api.SnsAPI;
import wechat.bean.SnsToken;

/**
 * Created by wenqing on 2016/5/23.
 */
public class SnsAPITest {
    public static String access_token = "ruoHbmuXnz8NgjhFVcrBihobsiRuwblATO7g2-O0oFkhV2BYPtW-UWQzW6Ofp1IL7CU_fzbZZ0JCneVk5g9rCnFZnTIWo-vcUvQBkYXuRohlLlJcoLFGjKETic_fMuTfXPReAJAJBD";

    @Test
    public void snsapi_userinfoGetCode() {
        String appId = "wx21d417fb548fdf71";
        String redirct_url = "http://xxd763955430.eicp.net/wechat/login";
        String code = SnsAPI.connectOauth2Authorize(appId, redirct_url, true, "");
        System.out.println(code);
        //03161HKW0nSmFQ1N3dKW0w7LKW061HK6
    }

    @Test
    public void snsapi_userinfoGetAccessToken() {
        String appId = "wx21d417fb548fdf71";
        String appSecret = "5da356be252e421c990ba644ee1ba131";
        String code = "03161HKW0nSmFQ1N3dKW0w7LKW061HK6";
        SnsToken snsToken = SnsAPI.oauth2AccessToken(appId, appSecret, code);
        System.out.println(snsToken);
    }

}
