package wechat.util;

import com.alibaba.fastjson.JSON;

/**
 * Created by wenqing on 2016/5/22.
 */
public class JsonUtil {
    private JsonUtil(){}

    public static <T> T parseObject(String json,Class<T> clazz){
        return JSON.parseObject(json, clazz);
    }

    public static String toJSONString(Object object){
        return JSON.toJSONString(object);
    }
}
