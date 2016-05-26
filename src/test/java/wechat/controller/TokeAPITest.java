package wechat.controller;

import org.junit.Test;
import wechat.api.TokenAPI;
import wechat.bean.token.Token;

/**
 * Created by wenqing on 2016/5/22.
 */
public class TokeAPITest {
    @Test
    public void generateToken() {
        //accessToken
        //mq8mMjOo-wEUA8kpxmOW6fYfX4Jayu4ce7aKdmucmSoKT7U5l3_5FuQ7Q83LmU8BkiikWOK_XOa96imP584DLjKQMLKuWxohNJB8Wi2JV-Ka0u1ZZ0vseo9KwYBHVuKoMFCaAAAZSP
        String appId = "wx21d417fb548fdf71";
        String appSecret = "5da356be252e421c990ba644ee1ba131";
        Token token = TokenAPI.token(appId, appSecret);
        System.out.println(token.getAccess_token());
        System.out.println(token.getExpires_in());
        System.out.println(token.getErrcode());
        System.out.println(token.getErrmsg());
    }
}

