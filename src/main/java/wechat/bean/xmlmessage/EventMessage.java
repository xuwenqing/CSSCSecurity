package wechat.bean.xmlmessage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by wenqing on 2016/5/22.
 */
@XmlRootElement(name="xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class EventMessage {

    @Override
    public String toString() {
        return "EventMessage{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", msgType='" + msgType + '\'' +
                ", event='" + event + '\'' +
                ", msgId='" + msgId + '\'' +
                ", content='" + content + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", mediaId='" + mediaId + '\'' +
                ", format='" + format + '\'' +
                ", recognition='" + recognition + '\'' +
                ", thumbMediaId='" + thumbMediaId + '\'' +
                ", location_X='" + location_X + '\'' +
                ", location_Y='" + location_Y + '\'' +
                ", scale='" + scale + '\'' +
                ", label='" + label + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    //base
    @XmlElement(name="ToUserName")
    private String toUserName; 		//开发者微信号

    @XmlElement(name="FromUserName")
    private String fromUserName;	//发送方帐号（一个OpenID）

    @XmlElement(name="CreateTime")
    private Integer createTime;		//消息创建时间 （整型）

    @XmlElement(name="MsgType")
    private String msgType;			//消息类型，event

    @XmlElement(name="Event")
    private String event;			//事件类型，subscribe(订阅)、unsubscribe(取消订阅)

    //接收普通消息------------------------------------ START
    @XmlElement(name="MsgId")
    private String msgId;			//消息ID号
    //文本
    @XmlElement(name="Content")
    private String content;			//文本消息内容
    //图片
    @XmlElement(name="PicUrl")
    private String picUrl;			//图片消息
    //媒体
    @XmlElement(name="MediaId")
    private String mediaId;			//mediaId 可以调用多媒体文件下载接口拉取数据
    //语音格式
    @XmlElement(name="Format")
    private String format;			//语音格式
    //语音识别
    @XmlElement(name="Recognition")
    private String recognition;		//开通语音识别功能的识别结果
    //视频
    @XmlElement(name="ThumbMediaId")
    private String thumbMediaId;	//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。

    //地理位置-地理位置维度
    @XmlElement(name="Location_X")
    private String location_X;

    //地理位置-地理位置经度
    @XmlElement(name="Location_Y")
    private String location_Y;

    //地理位置-地图缩放大小
    @XmlElement(name="Scale")
    private String scale;

    //地理位置-地理位置信息
    @XmlElement(name="Label")
    private String label;

    //链接
    @XmlElement(name="Title")
    private String title;
    @XmlElement(name="Description")
    private String description;
    @XmlElement(name="Url")
    private String url;
    //接收普通消息------------------------------------ END


    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Integer getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Integer createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getLocation_X() {
        return location_X;
    }

    public void setLocation_X(String location_X) {
        this.location_X = location_X;
    }

    public String getLocation_Y() {
        return location_Y;
    }

    public void setLocation_Y(String location_Y) {
        this.location_Y = location_Y;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

