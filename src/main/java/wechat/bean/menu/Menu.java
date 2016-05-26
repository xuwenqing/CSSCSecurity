package wechat.bean.menu;

import wechat.bean.BaseResult;

import java.util.List;

/**
 * Created by wenqing on 2016/5/22.
 */
public class Menu extends BaseResult {
    @Override
    public String toString() {
        return "Menu{" +
                "menu=" + menu +
                ", conditionalmenu=" + conditionalmenu +
                '}';
    }

    private MenuButtons menu;

    private List<MenuButtons> conditionalmenu;

    public MenuButtons getMenu() {
        return menu;
    }

    public void setMenu(MenuButtons menu) {
        this.menu = menu;
    }

    public List<MenuButtons> getConditionalmenu() {
        return conditionalmenu;
    }

    public void setConditionalmenu(List<MenuButtons> conditionalmenu) {
        this.conditionalmenu = conditionalmenu;
    }

}

