package audition;

import java.util.Arrays;

/**
 * @author codingprh
 * @create 2019-06-25 09:50
 */
public class App_2 {
    public static void main(String[] args) {


        int[] a = new int[3];
        int[] b = null;

        init(a, b);

        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));

    }

    private static void init(int[] a, int[] b) {
        a[0] = 1;
        b = a;
        b[1] = 2;
        a = null;
    }
}
