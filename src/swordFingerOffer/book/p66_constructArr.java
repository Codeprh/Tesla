package swordFingerOffer.book;

import java.util.Arrays;

/**
 * 描述:给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 *
 * @author Noah
 * @create 2020-08-31 8:04 上午
 */
public class p66_constructArr {

    public static void main(String[] args) {

        p66_constructArr app = new p66_constructArr();
        System.out.println(Arrays.toString(app.constructArr(new int[]{1, 2, 3, 4, 5})));

    }

    public int[] constructArr(int[] a) {

        if (a.length == 0) return new int[0];

        int[] b = new int[a.length];
        b[0] = 1;
        int tmp = 1;

        for (int i = 1; i < a.length; i++) {
            //下三角
            b[i] = b[i - 1] * a[i - 1];
        }
        for (int i = a.length - 2; i >= 0; i--) {
            //上三角
            tmp *= a[i + 1];
            //下三角 * 上三角
            b[i] *= tmp;
        }
        return b;
    }

}
