package swordFingerOffer.book;

/**
 * 描述:
 * 1～n整数中1出现的次数
 *
 * @author Noah
 * @create 2020-08-28 8:18 上午
 */
public class p43_countDigitOne {

    public static void main(String[] args) {

        p43_countDigitOne app = new p43_countDigitOne();
        System.out.println(app.countDigitOne(13));
    }

    /**
     * noah暴力算法,运行结果超时
     *
     * @param n
     * @return
     */
    public int countDigitOne(int n) {

        int count = 0;
        for (int i = 1; i <= n; i++) {

            String str = String.valueOf(i);
            char[] chars = str.toCharArray();

            for (char cc : chars) {
                if (cc == '1') {
                    count++;
                }
            }
        }
        return count;
    }
}
