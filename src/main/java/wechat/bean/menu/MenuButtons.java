package wechat.bean.menu;

import java.util.Arrays;

/**
 * Created by wenqing on 2016/5/22.
 */
public class MenuButtons {

    @Override
    public String toString() {
        return "MenuButtons{" +
                "button=" + Arrays.toString(button) +
                ", matchrule=" + matchrule +
                ", menuid='" + menuid + '\'' +
                '}';
    }

    private Button[] button;

    private Matchrule matchrule;

    private String menuid;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid;
    }


    public Matchrule getMatchrule() {
        return matchrule;
    }

    public void setMatchrule(Matchrule matchrule) {
        this.matchrule = matchrule;
    }
}
