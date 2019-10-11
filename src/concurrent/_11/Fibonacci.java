package concurrent._11;

/**
 * 描述:
 * 斐波那契数列
 *
 * @author Noah
 * @create 2019-10-09 09:25
 */
public class Fibonacci {
    /**
     * 实现斐波那契数列
     *
     * @param n
     * @return
     */
    public int[] fibonacci(int n) {

        int[] a = new int[n];
        a[0] = a[1] = 1;

        for (int i = 2; i < n; i++) {
            a[i] = a[i - 1] + a[i - 2];
        }

        return a;
    }

    public static void main(String[] args) {
        Fibonacci app = new Fibonacci();
        int[] a = app.fibonacci(40);
        for (int aa : a) {
            System.out.print(aa + "->");
        }
    }
}
