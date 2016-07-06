package controller.dto;

import model.User;

import java.util.List;

/**
 * Created by wenqing on 2016/7/6.
 */
public class UserUpdateDto {

    private User user;
    private List<Long> roleIds;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
