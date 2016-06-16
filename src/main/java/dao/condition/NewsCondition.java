package dao.condition;

import dao.base.PagerModel;

/**
 * Created by wenqing on 2016/6/6.
 */
public class NewsCondition extends PagerModel {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
