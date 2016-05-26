package wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import wechat.api.SnsAPI;
import wechat.api.UserAPI;
import wechat.bean.SnsToken;
import wechat.bean.user.User;
import wechat.bean.xmlmessage.EventMessage;
import wechat.bean.xmlmessage.XMLMessage;
import wechat.bean.xmlmessage.XMLTextMessage;
import wechat.util.SignUtil;
import wechat.util.XMLConverUtil;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@Controller
@RequestMapping("/wechat")
public class WechatController {

//    NewPeanuthull client 2.8.0.9515
//    诊断域名: xxd763955430.eicp.net
//    检测DNS服务器IP地址: 103.44.145.245
//
//    域名IP地址指向: 61.174.40.245
//    花生壳客户端IP: 211.161.247.196
//    经检测,您的域名已开启花生壳映射.
//
//    正在测试映射 xxd763955430.eicp.net:80->成功
//    局域网服务器 127.0.0.1:8080->成功

//    appid : wx70090a606eb9a82f
//    appsecret : 62c1cca0c657df2f0253afa63baf191e
//
//    http://xxd763955430.eicp.net/wechat/valid?echostr=111&signature=222&timestamp=333&nonce=444
//    http://localhost:8080/wechat/valid?echostr=111&signature=222&timestamp=333&nonce=444

    public static String access_token = "ruoHbmuXnz8NgjhFVcrBihobsiRuwblATO7g2-O0oFkhV2BYPtW-UWQzW6Ofp1IL7CU_fzbZZ0JCneVk5g9rCnFZnTIWo-vcUvQBkYXuRohlLlJcoLFGjKETic_fMuTfXPReAJAJBD";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void developValid(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "echostr")String echostr,
                             @RequestParam(value = "signature")String signature,
                             @RequestParam(value = "timestamp")String timestamp,
                             @RequestParam(value = "nonce")String nonce) throws IOException {

        System.out.println("echostr" + echostr);
        System.out.println("signature" + signature);
        System.out.println("timestamp" + timestamp);
        System.out.println("nonce" + nonce);
        System.out.println("get");

        ServletOutputStream outputStream = response.getOutputStream();

        //验证请求签名
        if(SignUtil.checkSignature(signature, timestamp, nonce)){
            outputStreamWrite(outputStream, echostr);
        }
        else {
            System.out.println("The request signature is invalid");
        }

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void receriveMessage(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(value = "signature")String signature,
                                @RequestParam(value = "timestamp")String timestamp,
                                @RequestParam(value = "nonce")String nonce ) throws IOException {


        String echostr = request.getParameter("echostr");
        ServletInputStream inputStream = request.getInputStream();
        ServletOutputStream outputStream = response.getOutputStream();

        //首次请求申请验证,返回echostr
        if(echostr != null){
            outputStreamWrite(outputStream, echostr);
            return;
        }

        //验证请求签名
        if(!SignUtil.checkSignature(signature, timestamp, nonce)){
            System.out.println("The request signature is invalid");
            return;
        }

        if(inputStream != null){
            //转换XML
            EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, inputStream);
            System.out.println(eventMessage);

            //创建回复
            XMLMessage xmlTextMessage = new XMLTextMessage(
                    eventMessage.getFromUserName(),
                    eventMessage.getToUserName(),
                    "www.ecust.edu.cn");

            //回复
            xmlTextMessage.outputStreamWrite(outputStream);
            return;
        }
        outputStreamWrite(outputStream,"");

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getUserInfo(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(value = "code", required = false) String code) {

        if(Objects.equals(code,"")) {
            System.out.println("用户未授权");
        }
        else {
            System.out.println("code：" + code);
            String appId = "wx21d417fb548fdf71";
            String appSecret = "5da356be252e421c990ba644ee1ba131";
            SnsToken snsToken = SnsAPI.oauth2AccessToken(appId, appSecret, code);
            System.out.println(snsToken);
            //User user =  SnsAPI.userinfo(snsToken.getAccess_token(),snsToken.getOpenid(),"zh_CN");
            User user = UserAPI.userInfo(access_token, snsToken.getOpenid());
            System.out.println(user);
            request.setAttribute("username", user.getNickname());
            request.setAttribute("country", user.getCountry());
            request.setAttribute("headimgurl", user.getHeadimgurl());
        }

        return "userinfo";

    }

    /**
     * 数据流输出
     * @param outputStream
     * @param text
     * @return
     */
    private boolean outputStreamWrite(OutputStream outputStream,String text){
        try {
            outputStream.write(text.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        return true;
    }

}