package dao.base;

/**
 * Created by wenqing on 2016/5/26.
 */
public class PagerModel {
    private Integer start;
    private Integer limit;

    private String sortby;
    private String order;

    public static class Builder {
        private Integer start;
        private Integer limit;
        private String sortby;
        private String order;

        public Builder() {

        }

        public Builder setStart(Integer start) {
            this.start = start;
            return this;
        }

        public Builder setLimit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder setSortby(String sortby) {
            this.sortby = sortby;
            return this;
        }

        public Builder setOrder(String order) {
            this.order = order;
            return this;
        }

        public PagerModel build() {
            return new PagerModel(this);
        }
    }

    public PagerModel() {
        this.start = 0;
        this.limit = 10;
        this.sortby = null;
        this.order = "desc";
    }

    public PagerModel(Builder builder) {
        this();
        setStart(builder.start);
        setLimit(builder.limit);
        setOrder(builder.order);
        setSortby(builder.sortby);
    }

    /**
     * 分页开始
     * @return
     */
    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * 分页数量
     * @return
     */
    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


    /**
     * 排序
     * @return
     */
    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getSortby() {
        return sortby;
    }

    public void setSortby(String sortby) {
        this.sortby = sortby;
    }

    @Override
    public String toString() {
        return "start:" + start + ",limit:" + limit;
    }
}
