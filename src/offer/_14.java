package offer;

/**
 * 描述:
 * 剪绳子
 * <p>
 * 给你一根⻓度为 n 绳子，请把绳子剪成 m 段（m、n 都是整数，2≤n≤58 并且 m≥2）。
 * 每段的绳子的⻓度记为 k[0]、k[1]、......、k[m] 。 k[0]k[1] ... k[m] 可能的最大乘积是多少?
 * 例如当绳子的⻓度是 8 时，我们把它剪成⻓度分别为 2、3、3 的三段，此时得到最大的乘积 18。
 *
 * @author Noah
 * @create 2020-05-05 22:04
 */
public class _14 {

    public static void main(String[] args) {
        System.out.println("最大乘机=" + methodV1(8));


    }

    /**
     * 动态规划法:递归转循环处理
     * ⻓度为 2，只可能剪成⻓度为 1 的两段，因此 f(2)=1
     * ⻓度为 3，剪成⻓度分别为 1 和 2 的两段，乘积比较大，因此 f(3) = 2
     * ⻓度为 n，在剪第一刀的时候，有 n-1 种可能的选择，剪出来的绳子又可以继续剪，可以看出，原 问题可以划分为子问题，子问题又有重复子问题。
     *
     * @param length
     */
    private static int methodV1(int length) {

        if (length < 4) {
            return length - 1;
        }

        int[] res = new int[length + 1];
        res[1] = 1;
        res[2] = 2;
        res[3] = 3;

        for (int i = 4; i <= length; ++i) {

            System.out.println("i=" + i + ",j循环的次数=" + (i / 2));

            for (int j = 1; j < i / 2 + 1; ++j) {
                res[i] = Math.max(res[i], res[j] * res[i - j]);
                System.out.println("i=" + i + "j=" + j + ",val=" + res[i]);
            }
        }
        return res[length];
    }
}
