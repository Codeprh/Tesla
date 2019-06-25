package leetcode;

/**
 * @author codingprh
 * @create 2019-06-25 15:37
 */
public class _292 {
    public boolean canWinNim(int n) {
        //限定条件为4的时候，自己不能先手就行
        return n % 4 != 0;
    }
}
