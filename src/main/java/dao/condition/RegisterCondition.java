package dao.condition;

import dao.base.PagerModel;

import java.util.Date;

/**
 * Created by wenqing on 2016/6/2.
 */
public class RegisterCondition extends PagerModel {
    private Date startTime;
    private Date endTime;

    public RegisterCondition() {
        super();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
