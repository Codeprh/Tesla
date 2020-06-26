package offer;

/**
 * 描述:
 * 二进制中1的个数
 *
 * @author Noah
 * @create 2020-05-05 20:31
 */
public class _15 {

    public static void main(String[] args) {
        noahTest();
        //System.out.println(binarySumOne_V3(9));
    }

    /**
     * 最佳利用javaApi
     * <p>
     * 统计二进制中1的个数
     *
     * @return
     */
    private static int binarySumOne(int value) {

        int b = Integer.bitCount(value);
        String s = Integer.toBinaryString(value);

        System.out.println("二进制=" + s);
        System.out.println("二进制1的长度=" + b);

        return 1;
    }

    /**
     * 求二进制中1的个数
     * 利用i=1，每次左移一位，后面补0，每次&n运算
     *
     * @param n 整数
     * @return 该整数的二进制中1的个数
     */
    public static int binarySumOne_V2(int n) {

        int i = 1;
        int cnt = 0;

        //控制条件：32次循环
        while (i != 0) {

            if ((n & i) != 0) {
                ++cnt;
            }

            i <<= 1;
            System.out.println(Integer.toBinaryString(i));
        }
        return cnt;
    }

    /**
     * 求二进制中1的个数
     * 利用n&(n-1)，每次把最右位的1转换为0，直至n=0为止
     *
     * @param n
     * @return
     */
    public static int binarySumOne_V3(int n) {
        int cut = 0;
        while (n != 0) {
            ++cut;
            n &= (n - 1);
        }
        return cut;
    }

    /**
     * 测试如何进行&运算
     */
    private static void noahTest() {

        int cur = 1;
        int value = 9;

        for (int i = 0; i < 10; i++) {

            cur <<= 1;

            System.out.println(Integer.toBinaryString(cur) + "," + cur);

        }
    }
}
