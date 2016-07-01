package interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 校验请求，验证是否可以进行正常请求，如果没有权限，则停止继续进行处理。
 * Created by wenqing on 2016/5/26.
 */
public class CheckTokenInterceptorAdapter extends HandlerInterceptorAdapter {

    private List<String> allowUrls;

    public void setAllowUrls(List<String> allowUrls) {
        this.allowUrls = allowUrls;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        String url = request.getServletPath();
//
//        for (String passUrl : allowUrls) {
//            if (url.matches(passUrl))
//                return true;
//        }
//
//        String token = request.getSession().getAttribute("token").toString();
//
//        if(token == null) {
//            return false;
//        }
//
       return true;
    }
}
