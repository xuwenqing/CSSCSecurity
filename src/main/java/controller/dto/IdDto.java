package controller.dto;

/**
 * Created by wenqing on 2016/7/2.
 */
public class IdDto {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdDto{" +
                "id='" + id + '\'' +
                '}';
    }
}
