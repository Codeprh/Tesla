package swordFingerOffer.book;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 描述:输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 *
 * @author Noah
 * @create 2020-09-01 8:24 上午
 */
public class p17_printNumbers {
    public static void main(String[] args) {

        p17_printNumbers app = new p17_printNumbers();
        System.out.println(Arrays.toString(app.printNumbers(3)));
    }

    /**
     * 进阶api
     *
     * @param n
     * @return
     */
    public int[] printNumbers_right(int n) {
        int end = (int) Math.pow(10, n) - 1;
        int[] res = new int[end];
        for (int i = 0; i < end; i++)
            res[i] = i + 1;
        return res;
    }


    /**
     * noah版本：直接循环处理
     *
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append("9");
        }
        return IntStream.rangeClosed(1, Integer.parseInt(sb.toString())).mapToLong(i -> i).mapToInt(o -> (int) o).toArray();
    }
}
