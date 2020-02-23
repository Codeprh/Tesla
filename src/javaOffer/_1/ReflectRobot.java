package javaOffer._1;

/**
 * 描述:
 *
 * @author Noah
 * @create 2020-02-23 09:29
 */
public class ReflectRobot {

    static {
        System.out.println("hello ReflectRobot");
    }

    private String name;

    public void sayHi(String hello) {
        System.out.println(hello + "," + name);

    }

    private String throwHello(String tag) {
        return "hello," + tag;
    }
}
