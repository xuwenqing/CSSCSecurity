package wechat.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import wechat.bean.user.User;
import wechat.client.LocalHttpClient;
import wechat.util.EmojiUtil;

/**
 * Created by wenqing on 2016/5/23.
 */
public class UserAPI extends BaseAPI {

    /**
     * 获取用户基本信息
     * @param access_token
     * @param openid
     * @return
     */
    public static User userInfo(String access_token,String openid){
        return userInfo(access_token, openid, 0);
    }

    /**
     * 获取用户基本信息
     * @since 2.7.1
     * @param access_token
     * @param openid
     * @param emoji 表情解析方式<br>
     * 0 		  不设置 <br>
     * 1 HtmlHex 格式<br>
     * 2 HtmlTag 格式<br>
     * 3 Alias  格式<br>
     * 4 HtmlDec 格式<br>
     * 5 PureText 纯文本<br>
     * @return
     */
    public static User userInfo(String access_token,String openid,int emoji){
        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(BASE_URI+"/cgi-bin/user/info")
                .addParameter(PARAM_ACCESS_TOKEN,access_token)
                .addParameter("openid",openid)
                .addParameter("lang","zh_CN")
                .build();
        User user = LocalHttpClient.executeJsonResult(httpUriRequest, User.class);
        if(emoji != 0 && user != null && user.getNickname() != null){
            user.setNickname_emoji(EmojiUtil.parse(user.getNickname(), emoji));
        }
        return user;
    }


}
