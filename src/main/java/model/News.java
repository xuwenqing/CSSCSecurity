package model;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", newsclassid=" + newsclassid +
                ", title='" + title + '\'' +
                ", status=" + status +
                ", author='" + author + '\'' +
                ", createtime=" + createtime +
                ", content='" + content + '\'' +
                '}';
    }

    private Integer id;

    private Integer newsclassid;

    private String title;

    private Integer status;

    private String author;

    private Date createtime;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNewsclassid() {
        return newsclassid;
    }

    public void setNewsclassid(Integer newsclassid) {
        this.newsclassid = newsclassid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}