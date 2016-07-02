package controller.dto;

/**
 * Created by wenqing on 2016/7/2.
 */
public class IdDto {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdDto{" +
                "id='" + id + '\'' +
                '}';
    }
}
