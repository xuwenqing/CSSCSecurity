package dao.condition;

import dao.base.PagerModel;

/**
 * Created by wenqing on 2016/6/29.
 */
public class HealthCondition extends PagerModel {
    private String name;
    private String keywords;

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
