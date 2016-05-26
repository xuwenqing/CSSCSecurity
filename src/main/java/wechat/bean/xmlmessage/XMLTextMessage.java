package wechat.bean.xmlmessage;

/**
 * Created by wenqing on 2016/5/22.
 */
public class XMLTextMessage extends XMLMessage{

    private String content;

    public XMLTextMessage(String toUserName, String fromUserName,String content) {
        super(toUserName, fromUserName, "text");
        this.content = content;
    }


    @Override
    public String subXML() {
        return "<Content><![CDATA["+content+"]]></Content>";
    }


}
