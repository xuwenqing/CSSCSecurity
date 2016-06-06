package interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * Created by wenqing on 2016/5/26.
 */
public class CustomizedHandlerInterceptor extends HandlerInterceptorAdapter {

    private List<String> allowUrls;

    public void setAllowUrls(List<String> allowUrls) {
        this.allowUrls = allowUrls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getServletPath();
        if(Objects.equals("/", url)) {
            return true;
        }

        for (int i = 0; i < allowUrls.size(); i++) {
            if (url.startsWith(allowUrls.get(i))) {
                return true;
            }
        }

        Object token = request.getSession().getAttribute("token");

        if(token == null) {
            return false;
        }

        return true;
    }
}
