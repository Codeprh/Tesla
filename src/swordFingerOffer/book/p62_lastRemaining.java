package swordFingerOffer.book;

import java.util.ArrayList;

/**
 * 描述:
 * <p>
 * 0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字。求出这个圆圈里剩下的最后一个数字。
 * <p>
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *
 * @author Noah
 * @create 2020-08-31 7:08 上午
 */
public class p62_lastRemaining {

    public static void main(String[] args) {

        p62_lastRemaining app = new p62_lastRemaining();
        System.out.println(app.lastRemaining_right(5, 3));
    }

    /**
     * noah：无版本，没有理解到题目的意思。每次删除数组的第m位数后，重新生成的环，继续删除第M位
     * 正解：每次在第m位删除元素之后，继续在当前位置删除第m位数字
     *
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining_right(int n, int m) {

        ArrayList<Integer> list = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        int idx = 0;

        while (n > 1) {
            System.out.println(idx + m - 1 + "%" + n + "=" + ((idx + m - 1) % n));
            idx = (idx + m - 1) % n;
            list.remove(idx);
            n--;

        }
        return list.get(0);
    }


}
