package noahjdk;

/**
 * 描述:
 * cc
 *
 * @author Noah
 * @create 2020-02-07 16:03
 */
public class TestA {
    private static int x = 100;

    public static void main(String[] args) {
        TestA a1 = new TestA();
        a1.x++;

        TestA a2 = new TestA();
        a2.x++;

        a1 = new TestA();
        a1.x++;

        TestA.x--;
        System.out.println("x=" + x);
    }
}
