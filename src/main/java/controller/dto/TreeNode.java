package controller.dto;

/**
 * Created by wenqing on 2016/7/13.
 */
public class TreeNode {

    private long id;
    private long pId;
    private String name;
    private String title;
    private boolean checked;
    private boolean open;
    private boolean isHidden;

    public TreeNode(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", checked=" + checked +
                ", open=" + open +
                ", isHidden=" + isHidden +
                '}';
    }



    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeNode treeNode = (TreeNode) o;

        return id == treeNode.id;

    }

    public TreeNode(long id, long pId, String name, String title, boolean checked, boolean open, boolean isHidden) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.title = title;
        this.checked = checked;
        this.open = open;
        this.isHidden = isHidden;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public long getId() {
        return id;
    }

    public long getpId() {
        return pId;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public boolean isChecked() {
        return checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
