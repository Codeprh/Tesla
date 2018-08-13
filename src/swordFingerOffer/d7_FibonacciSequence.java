package swordFingerOffer;

/**
 * 斐波那契数列
 * <p>
 * 题目描述：求菲波那契数列的第 n 项，n <= 39。
 */
public class d7_FibonacciSequence {
    private Integer[] result = new Integer[40];

    /**
     * 传统求解：斐波那契数列
     * 时间复杂度为O(N) 空间复杂度为0(1)
     *
     * @param n
     * @return
     */
    public Integer simpleAndRude(Integer n) {
        if (n <= 1) {
            return n;
        }
        Integer p1 = 0, p2 = 1;
        Integer temporaryValue = 0;
        System.out.println("p1=" + p1);
        System.out.println("p2=" + p2);
        for (Integer i = 2; i <= n; i++) {
            temporaryValue = p1 + p2;
            System.out.println("p" + i + "=" + temporaryValue);
            p1 = p2;
            p2 = temporaryValue;
        }
        return temporaryValue;

    }

    /**
     * 最优实现方案
     * 时间复杂度为O(1).控件复杂度为O(n)
     *
     * @return
     */
    public Integer optimization(Integer n) {
        return result[n];
    }

    /**
     * 利用初始化函数，初始化
     */
    public d7_FibonacciSequence() {
        result[0] = 0;
        result[1] = 1;
        for (Integer i = 2; i < result.length; i++) {
            result[i] = result[i - 1] + result[i - 2];
        }

    }
    public static void main(String[] args) {
        d7_FibonacciSequence app = new d7_FibonacciSequence();
        System.out.println(app.simpleAndRude(5));
        System.out.println(app.optimization(5));

    }
}
