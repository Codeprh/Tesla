package javaOffer._2;

/**
 * 描述:
 *
 * @author Noah
 * @create 2020-02-25 10:06
 */
public class TestSample {
    public static void main(String[] args) {

        synchronized (TestSample.class) {
            System.out.println("hello");
        }
    }
}
