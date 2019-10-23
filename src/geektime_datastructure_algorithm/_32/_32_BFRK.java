package geektime_datastructure_algorithm._32;

/**
 * 描述:
 * 字符串匹配算法
 * <p>
 * BF：暴力破解法
 * RK：BF的升级版，hash思想
 *
 * @author Noah
 * @create 2019-10-23 14:15
 */
public class _32_BFRK {

    public static void main(String[] args) {

        String a = "abcd644632514462146412ea";
        String b = "bc";

        char[] a1 = a.toCharArray();
        int fIndex = myRk(a, b);

        if (fIndex >= 0) {
            for (int i = b.length(); i > 0; i--) {
                System.out.print(a1[fIndex++]);
            }
            System.out.println("---------------");
        }
    }

    /**
     * 返回成功匹配字符串：首个字符的下标
     *
     * @param a ：主串
     * @param b ：模式串
     * @return
     */
    public static int myBf(String a, String b) {
        //字符串的长度
        int m = a.length();
        int n = b.length();
        int k;
        //string 转为char []
        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();
        //循环条件控制：主串的遍历剩余字符保证长度要大于等模式串的长度，也就是只需要遍历到主串m-n的位置
        for (int i = 0; i < m - n + 1; i++) {
            //用于比较结果
            k = 0;
            for (int j = 0; j < n; j++) {
                if (a1[i + j] == b1[j]) {
                    k++;
                } else {
                    break;
                }
            }
            if (k == n) {
                return i;
            }

        }
        return -1;
    }

    public static int myRk(String a, String b) {
        //字符串的长度
        int m = a.length();
        int n = b.length();
        int k;
        //string 转为char []
        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();
        int[] hash = new int[m - n + 1];

        //模式串的hash值
        int bHash = char2hash(b1);


        //计算主串所有可能串的hash值
        for (int i = 0; i < m - n + 1; i++) {

            char[] temp = new char[n];
            for (int j = 0; j < n; j++) {
                temp[j] = a1[i + j];
            }
            //计算字符的hash值
            hash[i] = char2hash(temp);
        }

        for (int i = 0; i < m - n + 1; i++) {
            if (hash[i] == bHash) {
                //解决：hash冲突的时候
                return i;
            }
        }
        return -1;
    }

    /**
     * 同
     * java.lang.String#hashCode()
     *
     * @return
     */
    private static int char2hash(char[] arrs) {
        int r = 0;
        for (char a : arrs) {
            r = 31 * r + a;
            //解决：hash冲突的时候，要比较值：abcd / ad=bc冲突
            //r += (int) a;
        }
        return r;
    }

    /**
     * 返回成功匹配字符串：首个字符的下标
     *
     * @param a ：主串
     * @param b ：模式串
     * @return
     */
    public static int bF(String a, String b) {
        int m = a.length(), n = b.length(), k;
        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();
        for (int i = 0; i <= m - n; i++) {
            k = 0;
            for (int j = 0; j < n; j++) {
                if (a1[i + j] == b1[j]) {
                    k++;
                } else {
                    break;
                }
            }
            if (k == n) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回成功匹配字符串：首个字符的下标
     * <p>
     * 利用hash来计算，bf的升级版.
     * <p>
     * 比如要处理的字符串只包含a~z这26个小写字母，那我们就用二十六进制来表示一个字符串。我们把a~z这26个字符映射到0 ~25这26个数字，a就表示0，b就表示1，以此类推，z表示25。
     *
     * @param a
     * @param b
     * @return
     */
    public static int rK(String a, String b) {
        int m = a.length(), n = b.length(), s, j;
        //计算m-n+1所有字符的hash
        int[] hash = new int[m - n + 1];
        //26进制
        int[] table = new int[26];
        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();
        s = 1;
        //将26的次方存储在一个表里，取的时候直接用,虽然溢出，但没啥问题
        for (j = 0; j < 26; j++) {
            table[j] = s;
            s *= 26;
        }
        for (int i = 0; i <= m - n; i++) {
            s = 0;
            for (j = 0; j < n; j++) {
                //计算主串的所有组合hash值
                s += (a1[i + j] - 'a') * table[n - 1 - j];
            }
            hash[i] = s;
        }
        s = 0;
        for (j = 0; j < n; j++) {
            //计算模式串的hash值
            s += (b1[j] - 'a') * table[n - 1 - j];
        }
        for (j = 0; j < m - n + 1; j++) {
            if (hash[j] == s) {
                return j;
            }
        }
        return -1;
    }

}
