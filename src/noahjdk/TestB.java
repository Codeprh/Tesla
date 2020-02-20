package noahjdk;

/**
 * 描述:
 * cc
 *
 * @author Noah
 * @create 2020-02-07 16:11
 */
public class TestB {
    public int a = 0;

    public void method() {
        try {
            int bb = 1 / 0;
        } catch (Exception e) {
            a = 1;
            return;
        } finally {
            a = 2;
        }
        a = 3;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
}
