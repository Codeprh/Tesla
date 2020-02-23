package javaOffer._1;

/**
 * 描述:
 * class.forName和T.ClassLoder的区别
 *
 * @author Noah
 * @create 2020-02-23 11:39
 */
public class LoaderDiffSample {

    public static void main(String[] args) throws ClassNotFoundException {

        ClassLoader classLoader = ReflectRobot.class.getClassLoader();

        Class.forName("javaOffer._1.ReflectRobot");

    }
}
