package noahjdk;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 测试
 *
 * @author Noah
 * @create 2020-02-13 14:05
 */
public class TestC {

    public List<List<Integer>> n(int n) {

        List<List<Integer>> r = new ArrayList<>();
        List<List<Integer>> rr = new ArrayList<>();

        List<Integer> rt = new ArrayList<>();
        List<Integer> rt2 = new ArrayList<>();

        for (int i = 1, j = n; i <= n; i++) {
            rt.add(i);
            rt2.add(j);
            j--;
        }

        for (int i = 0; i < n; i++) {
            r.add(rt);
            rr.add(rt2);
        }

        
        return r;
    }


    public static void main(String[] args) {
        TestC app = new TestC();
        app.n(3);

    }
}
