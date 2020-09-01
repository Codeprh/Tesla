package swordFingerOffer.book;

/**
 * 描述:
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * @author Noah
 * @create 2020-09-01 7:30 上午
 */
public class p10_fib {
    public static void main(String[] args) {
        p10_fib app = new p10_fib();
        System.out.println(app.fib(2));
    }


    /**
     * 动态规划：f(n+1)=f(n)+f(n−1)
     *
     * @param n
     * @return
     */
    public int fib_right(int n) {
        int a = 0, b = 1, sum;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;
            b = sum;
        }
        return a;
    }

    /**
     * noah版本：递归实现斐波那契数列。
     * 递归的坏：采取从下往上的方法，把计算过的中间项保存起来，避免重复计算导致递归调用栈溢出
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 2 || n == 1) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }
        return fib(n - 1) + fib(n - 2);

    }
}
