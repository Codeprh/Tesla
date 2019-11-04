package geektime_datastructure_algorithm._10;

/**
 * 描述:
 * 递归求解n台阶问题:每次只能走一格或者2格
 *
 * todo：使用散列函数，解决重复计算
 *
 * @author Noah
 * @create 2019-10-31 09:45
 */
public class _10_Recursive_NStep {
    /**
     * 递归求解
     * <p>
     * 当第一步走了1格，或者第一步走了2格
     * 递归公式：f(n)=f(n-1)+f(n-2)
     * 终止条件：f(1)=1,f(2)=2
     *
     * @param n
     * @return
     */
    public int recursive(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return recursive(n - 1) + recursive(n - 2);
    }

    public static void main(String[] args) {

        _10_Recursive_NStep app = new _10_Recursive_NStep();
        System.out.println("台阶总共走法：" + app.recursive(10));
    }
}
