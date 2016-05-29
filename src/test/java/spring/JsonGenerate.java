package spring;

import com.alibaba.fastjson.JSON;
import com.sun.org.apache.bcel.internal.generic.LADD;
import model.Law;
import org.junit.Test;

/**
 * Created by wenqing on 2016/5/29.
 */
public class JsonGenerate {

    @Test
    public void generateLaw() {
        Law law = new Law();
        law.setTitle("title");
        law.setContent("content");

        System.out.println(JSON.toJSON(law));
    }


}
