package geektime_datastructure_algorithm._10;

/**
 * 描述:
 * 递归代码修改为非递归代码
 *
 * @author Noah
 * @create 2019-11-04 08:05
 */
public class _10_No_Recursive {
    /**
     * 把递推公式：f(x)=f(x-1)+1、终止条件f(1)=1
     * <p>
     * 修改为非递归的代码
     * <p>
     * 场景：我在电影第几排修改为非递归
     *
     * @param n
     */
    public int f(int n) {
        int ret = 1;

        for (int i = 2; i < n; i++) {
            ret += 1;
        }

        return ret;
    }

    /**
     * 参考实现
     * n台阶问题：非递归解法
     *
     * @param n
     * @return
     */
    public int nStep(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int ret = 0;
        int pre = 2;
        int prepre = 1;
        for (int i = 3; i <= n; ++i) {
            ret = pre + prepre;
            prepre = pre;
            pre = ret;
        }
        return ret;
    }
}
