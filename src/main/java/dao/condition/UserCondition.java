package dao.condition;

import dao.base.PagerModel;

/**
 * Created by wenqing on 2016/6/6.
 */
public class UserCondition extends PagerModel {
    private String username;
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
