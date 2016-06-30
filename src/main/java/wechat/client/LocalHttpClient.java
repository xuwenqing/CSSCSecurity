package wechat.client;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Created by wenqing on 2016/5/22.
 */
public class LocalHttpClient {
    private static final Logger logger = LoggerFactory.getLogger(LocalHttpClient.class);

    private static int timeout = 5000;

    private static int retryExecutionCount = 2;

    protected static CloseableHttpClient httpClient = HttpClientFactory.createHttpClient(100,10,timeout,retryExecutionCount);

    public static CloseableHttpResponse execute(HttpUriRequest request){
        loggerRequest(request);
        try {
            return httpClient.execute(request, HttpClientContext.create());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    public static <T> T execute(HttpUriRequest request,ResponseHandler<T> responseHandler){
        String uriId = loggerRequest(request);
        if(responseHandler instanceof LocalResponseHandler){
            LocalResponseHandler lrh = (LocalResponseHandler) responseHandler;
            lrh.setUriId(uriId);
        }
        try {
            return httpClient.execute(request, responseHandler,HttpClientContext.create());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 数据返回自动JSON对象解析
     * @param request
     * @param clazz
     * @return
     */
    public static <T> T executeJsonResult(HttpUriRequest request,Class<T> clazz){
        return execute(request,JsonResponseHandler.createResponseHandler(clazz));
    }

    /**
     * 数据返回自动XML对象解析
     * @param request
     * @param clazz
     * @return
     */
    public static <T> T executeXmlResult(HttpUriRequest request,Class<T> clazz){
        return execute(request,XmlResponseHandler.createResponseHandler(clazz));
    }


    /**
     * 日志记录
     * @param request
     */
    private static String loggerRequest(HttpUriRequest request){
        String id = UUID.randomUUID().toString();
        if(logger.isInfoEnabled()||logger.isDebugEnabled()){
            if(request instanceof HttpEntityEnclosingRequestBase){
                HttpEntityEnclosingRequestBase request_base = (HttpEntityEnclosingRequestBase)request;
                HttpEntity entity = request_base.getEntity();
                String content = null;
                //MULTIPART_FORM_DATA 请求类型判断
                if(entity.getContentType().toString().indexOf(ContentType.MULTIPART_FORM_DATA.getMimeType()) == -1){
                    try {
                        content = EntityUtils.toString(entity);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error(e.getMessage());
                    }
                }
                logger.info("URI[{}] {} {} ContentLength:{} Content:{}",
                        id,
                        request.getURI().toString(),
                        entity.getContentType(),
                        entity.getContentLength(),
                        content == null?"multipart_form_data":content);
            }else{
                logger.info("URI[{}] {}",id,request.getURI().toString());
            }
        }
        return id;
    }
}
