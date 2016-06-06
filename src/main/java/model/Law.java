package model;

import java.io.Serializable;
import java.util.Date;

public class Law implements Serializable {
    @Override
    public String toString() {
        return "Law{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishInstitution='" + publishInstitution + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", publishTime=" + publishTime +
                ", activeTime=" + activeTime +
                ", category='" + category + '\'' +
                ", viewCount=" + viewCount +
                ", content='" + content + '\'' +
                '}';
    }

    private Integer id;

    private String title;

    private String publishInstitution;

    private String documentNumber;

    private Date publishTime;

    private Date activeTime;

    private String category;

    private Integer viewCount;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getPublishInstitution() {
        return publishInstitution;
    }

    public void setPublishInstitution(String publishInstitution) {
        this.publishInstitution = publishInstitution == null ? null : publishInstitution.trim();
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber == null ? null : documentNumber.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}