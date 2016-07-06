package controller.dto;

import model.Role;

import java.util.List;

/**
 * Created by wenqing on 2016/7/6.
 */
public class RoleUpdateDto {
    private Role role;
    private List<Long> ids;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
