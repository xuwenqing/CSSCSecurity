package wechat.client;

/**
 * Created by wenqing on 2016/5/22.
 */
public class LocalResponseHandler {
    protected String uriId;

    protected long startTime = System.currentTimeMillis();

    public String getUriId() {
        return uriId;
    }

    public void setUriId(String uriId) {
        this.uriId = uriId;
    }
}
