package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author codingprh
 * @create 2019-06-26 13:43
 */
public class _89 {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        int len = 1 << n;
        for (int i = 0; i < len; i++) {
            result.add(i);
        }
        System.out.println(result.toString());
        return result;
    }

    public static void main(String[] args) {
        _89 start = new _89();
        start.grayCode(2);
    }
}
