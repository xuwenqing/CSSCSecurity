package model;

import java.io.Serializable;

public class NewsSort implements Serializable {
    @Override
    public String toString() {
        return "NewsSort{" +
                "id=" + id +
                ", sortname='" + sortname + '\'' +
                ", sortlevel=" + sortlevel +
                '}';
    }

    private Integer id;

    private String sortname;

    private Integer sortlevel;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname == null ? null : sortname.trim();
    }

    public Integer getSortlevel() {
        return sortlevel;
    }

    public void setSortlevel(Integer sortlevel) {
        this.sortlevel = sortlevel;
    }
}