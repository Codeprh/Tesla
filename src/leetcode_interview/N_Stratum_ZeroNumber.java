package leetcode_interview;

/**
 * 描述:
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 *
 * @author Noah
 * @create 2020-01-07 22:15
 */
public class N_Stratum_ZeroNumber {

    public static void main(String[] args) {

        N_Stratum_ZeroNumber app = new N_Stratum_ZeroNumber();
        int n = 5;
        int r = app.trailingZeroes(n);

        String str = r + "";
        char[] chars = str.toCharArray();
        int zeroCount = 0;

        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '0') {
                ++zeroCount;
            }
        }

        System.out.println(n + "!=" + r + ",尾数0的数量=" + zeroCount);
    }

    /**
     * 错误实现，正确答案：leetcode._172
     *
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int r = trailingZeroes(n);
        String str = r + "";
        char[] chars = str.toCharArray();
        int zeroCount = 0;

        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '0') {
                ++zeroCount;
            }
        }
        return zeroCount;


    }

    public int recursive(int n) {
        if (n == 1) {
            return 1;
        }
        return n * recursive(n - 1);
    }

}
