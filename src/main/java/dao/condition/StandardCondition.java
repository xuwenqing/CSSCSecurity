package dao.condition;

import dao.base.PagerModel;

/**
 * Created by wenqing on 2016/6/29.
 */
public class StandardCondition extends PagerModel{
    private String name;
    private String keywords;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}
