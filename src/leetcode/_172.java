package leetcode;

/**
 * 描述:
 * n阶层尾数有多少个0
 *
 * @author Noah
 * @create 2020-01-07 22:39
 */
public class _172 {

    public static void main(String[] args) {
        _172 app = new _172();
        int n = 12;
        int ncount = app.nRecursive(n);
        int zeroCount = app.trailingZeroes(n);
        System.out.println(n + "!=" + ncount + "，mantissa zeroCount=" + zeroCount);
    }

    /**
     * 参考版
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            int N = i;
            while (N > 0) {
                if (N % 5 == 0) {
                    count++;
                    N /= 5;
                } else {
                    break;
                }
            }
        }
        return count;

    }

    public int nRecursive(int n) {
        if (n == 1) {
            return 1;
        }
        return n * nRecursive(n - 1);
    }
}
