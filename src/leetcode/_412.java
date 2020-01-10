package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述:
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 * <p>
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * <p>
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * <p>
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 *
 * n = 15,
 *
 * 返回:
 * [
 *     "1",
 *     "2",
 *     "Fizz",
 *     "4",
 *     "Buzz",
 *     "Fizz",
 *     "7",
 *     "8",
 *     "Fizz",
 *     "Buzz",
 *     "11",
 *     "Fizz",
 *     "13",
 *     "14",
 *     "FizzBuzz"
 * ]
 *
 * @author Noah
 * @create 2020-01-10 09:17
 */
public class _412 {

    public static void main(String[] args) {
        _412 app = new _412();
        System.out.println(app.fizzBuzz(15).toString());

    }

    /**
     * 自己实现第一版
     *
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {

        List<String> r = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                r.add("FizzBuzz");
            } else if (i % 3 == 0) {
                r.add("Fizz");
            } else if (i % 5 == 0) {
                r.add("Buzz");
            } else {
                r.add(String.valueOf(i));
            }
        }

        return r;
    }
}
