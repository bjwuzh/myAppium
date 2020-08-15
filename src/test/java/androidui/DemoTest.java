package androidui;

import base.AndroidUIBase;
import org.junit.jupiter.api.Test;

import java.io.File;

/**
 * @program: myAppium
 * @description:
 * @author: bjwuzh
 * @create: 2020-08-15 14:34
 **/
public class DemoTest extends AndroidUIBase {
    @Test
    void test(){
        File classpathRoot = new File(System.getProperty("user.dir"));
        System.out.println(classpathRoot);
    }
}